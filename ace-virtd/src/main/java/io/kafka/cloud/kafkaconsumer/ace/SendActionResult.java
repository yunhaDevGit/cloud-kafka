package io.kafka.cloud.kafkaconsumer.ace;

import io.kafka.cloud.kafkacommon.dto.ResultDto;
import io.kafka.cloud.kafkacommon.dto.VmDto;
import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_RESULT;
import io.kafka.cloud.kafkacommon.utils.constant.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SendActionResult<T> {

  @Value("${kafka.topic.result}")
  private String actionResultTopic;

  @Autowired
  private KafkaTemplate<String, ResultDto> resultKafkaTemplate;

  public void sendMessage(T dto, boolean action_result){

    ResultDto<T, ActionResult> resultDto = new ResultDto<>();
    resultDto.setDto(dto);
    if(action_result)
      resultDto.setAction_result(ACTION_RESULT.ACTION_SUCCESS);
    else
      resultDto.setAction_result(ACTION_RESULT.ACTION_FAILED);

    Message<ResultDto<T, ActionResult>> message = MessageBuilder
        .withPayload(resultDto)
        .setHeader(KafkaHeaders.TOPIC, actionResultTopic)
        .build();

    resultKafkaTemplate.send(message);
    System.out.println("SendActionResult - sendMessage " + message);
  }
}
