package io.kafka.cloud.kafkaproducer.service;

import static io.kafka.cloud.kafkacommon.utils.Constant.ACTION_CODE.VM_CREATE;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.kafka.cloud.kafkacommon.domain.Vm;
import io.kafka.cloud.kafkacommon.dto.VmDto;
import io.kafka.cloud.kafkacommon.mapper.VmMapper;
import io.kafka.cloud.kafkacommon.repository.VmRepository;
import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_CODE;
import io.kafka.cloud.kafkacommon.utils.kafkaqueue.QueueAction;
import io.kafka.cloud.kafkacommon.utils.kafkaqueue.QueueSender;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@ComponentScan(basePackages = "io.kafka.cloud.kafkacommon")
@Service
public class VmService {

  @Autowired
  private KafkaTemplate<String, VmDto> vmKafkaTemplate;

  @Autowired
  VmRepository vmRepository;

  @Autowired
  private QueueSender<ACTION_CODE> queueSender;

  private final VmMapper vmMapper = Mappers.getMapper(VmMapper.class);

  @Value("${kafka.topic.ace}")
  private String aceTopicName;

  @Transactional
  public String createVm(VmDto vmDto) {

    // db에 넣는 로직
    Vm vm = vmMapper.toEntity(vmDto);
    vmRepository.save(vm);

    System.out.println("VmService - createVm");

    QueueAction queueAction = QueueAction.<ACTION_CODE>builder()
        .actionCode(VM_CREATE)
        .actionId("vm_create actionId")
        .object(vmDto)
        .build();

    try {
      queueSender
          .sendAsync(aceTopicName, queueAction);
    } catch (JsonProcessingException e) {

    }

    return queueAction.getActionId();
  }

}
