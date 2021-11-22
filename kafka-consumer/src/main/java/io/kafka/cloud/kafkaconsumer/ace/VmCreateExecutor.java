package io.kafka.cloud.kafkaconsumer.ace;

import io.kafka.cloud.kafkacommon.dto.VmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class VmCreateExecutor {

  @Value("${kafka.topic.ace.result}")
  private String aceResultTopicName;

  @Autowired
  private KafkaTemplate<String, String> resultKafkaTemplate;

  @KafkaListener(topics = "${kafka.topic.ace.vm}", groupId = "${kafka.consumer.group.ace.vm}", containerFactory = "vmKafkaListenerContainerFactory")
  public void listenVmCreateMessage(@Payload VmDto vmDto, @Headers MessageHeaders messageHeaders) {
    System.out.println("Received Message: " + vmDto.toString() + " headers: " + messageHeaders);
    // vm 생성 로직

    // 생성 완료 메세지 전송 로직
    Message<String> message = MessageBuilder
        .withPayload("VmCreate Success")
        .setHeader(KafkaHeaders.TOPIC, aceResultTopicName)
        .build();

    System.out.println("VmExecutor-send message");

    resultKafkaTemplate.send(message);

  }
}
