package io.kafka.cloud.kafkaconsumer.ace;

import io.kafka.cloud.kafkacommon.dto.VmDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AceQueueReceiver {

  @KafkaListener(topics = "${kafka.topic.ace}", groupId = "cloudit", containerFactory = "kafkaListenerContainerFactory")
  public void getAction(String action) {
    System.out.println(action);
    // vm 생성 로직
  }
}
