package io.kafka.cloud.kafkaconsumer.ace;

import io.kafka.cloud.kafkacommon.domain.Vm;
import io.kafka.cloud.kafkacommon.dto.ResultDto;
import io.kafka.cloud.kafkacommon.dto.VmDto;
import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_CODE;
import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_RESULT;
import io.kafka.cloud.kafkacommon.utils.constant.ActionCode;
import io.kafka.cloud.kafkacommon.utils.constant.ActionResult;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@ComponentScan(basePackages = "io.kafka.cloud.kafkacommon")
@Component
public class AceQueueReceiver {

  @Value("${kafka.topic.result}")
  private String actionResultTopic;

  @Autowired
  private KafkaTemplate<String, ResultDto> resultKafkaTemplate;

  @KafkaListener(topics = "${kafka.topic.ace}", groupId = "${kafka.consumer.group.ace}", containerFactory = "aceKafkaListenerContainerFactory")
  public void getAction(String action) {
    System.out.println("AceQueueReceiver get message = " + action);

    JSONObject jsonObject = new JSONObject(action);
//    ACTION_CODE actionCode = (ACTION_CODE) jsonObject.get("actionCode");

    ACTION_CODE actionCode = ACTION_CODE.valueOf((String) jsonObject.get("actionCode"));

    boolean result = true;
    switch (actionCode){
      case VM_CREATE:
        // vm 생성 로직
        // result = true;
        break;
      case VM_DELETE:
        // vm 삭제 로직
        // result= true;
        break;
      default:
        // 해당 하는 액션이 없는 경우
        break;
    }



    ResultDto<VmDto, ActionResult> resultDto = new ResultDto<>();
    VmDto vmDto = new VmDto();
    vmDto.setId("vmId");
    vmDto.setCpuNum(8);

    resultDto.setDto(vmDto);
    resultDto.setAction_result(ACTION_RESULT.ACTION_SUCCESS);

    Message<ResultDto<VmDto, ActionResult>> message = MessageBuilder
        .withPayload(resultDto)
        .setHeader(KafkaHeaders.TOPIC, actionResultTopic)
        .build();

    resultKafkaTemplate.send(message);
  }
}
