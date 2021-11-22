package io.kafka.cloud.admd.result;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ReceiveResult {

  @KafkaListener(topics = "${kafka.topic.ace.result}", groupId = "${kafka.consumer.group.ace.result}", containerFactory = "resultKafkaListenerContainerFactory")
  public void listenVmCreateResultMessage(@Payload String result, @Headers MessageHeaders messageHeaders) {
    System.out.println("ReceiveResult" + result);
  }
}
