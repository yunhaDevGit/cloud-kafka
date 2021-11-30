package io.kafka.cloud.kafkaproducer.service;

import static io.kafka.cloud.kafkacommon.utils.Constant.ACTION_CODE.VM_CREATE;
import static io.kafka.cloud.kafkacommon.utils.Constant.ACTION_CODE.VM_DELETE;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kafka.cloud.kafkacommon.domain.Vm;
import io.kafka.cloud.kafkacommon.dto.VmDto;
import io.kafka.cloud.kafkacommon.mapper.VmMapper;
import io.kafka.cloud.kafkacommon.repository.VmRepository;
import io.kafka.cloud.kafkacommon.utils.kafkaqueue.QueueAction;
import io.kafka.cloud.kafkacommon.utils.kafkaqueue.ActionQueueSender;
import java.util.Optional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@ComponentScan(basePackages = "io.kafka.cloud.kafkacommon")
@Service
public class VmService {

  @Autowired
  VmRepository vmRepository;

  @Autowired
  private ActionQueueSender actionQueueSender;

  private final VmMapper vmMapper = Mappers.getMapper(VmMapper.class);

  @Value("${kafka.topic.ace}")
  private String aceTopicName;

  @Transactional
  public String createVm(VmDto vmDto) {

    // db에 넣는 로직
    Vm vm = vmMapper.toEntity(vmDto);
    vmRepository.save(vm);

    System.out.println("VmService - createVm");

    QueueAction queueAction = QueueAction.builder()
        .actionCode(VM_CREATE)
        .actionId("vm_create actionId")
        .dto(vmDto)
        .build();

    try {
      actionQueueSender
          .sendAsync(aceTopicName, queueAction);
    } catch (JsonProcessingException e) {

    }

    return queueAction.getActionId();
  }

  public String deleteVm(String id) {
    Optional<Vm> vm = vmRepository.findById(id);

    if(!vm.isPresent()){
      throw new IllegalArgumentException();
    }

    VmDto vmDto = vmMapper.toDto(vm);

    vmRepository.deleteById(id);

    System.out.println("VmService - deleteVm");

    QueueAction queueAction = QueueAction.builder()
        .actionCode(VM_DELETE)
        .actionId("vm_delete actionId")
        .dto(vmDto)
        .build();


    try {
      actionQueueSender
          .sendAsync(aceTopicName, queueAction);
    } catch (JsonProcessingException e) {

    }

    return queueAction.getActionId();
  }
}
