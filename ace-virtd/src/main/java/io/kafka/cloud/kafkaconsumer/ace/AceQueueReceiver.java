package io.kafka.cloud.kafkaconsumer.ace;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AceQueueReceiver {

  @KafkaListener(topics = "${kafka.topic.ace}", groupId = "${kafka.consumer.group.ace}", containerFactory = "aceKafkaListenerContainerFactory")
  public void getAction(String action) {
    System.out.println("AceQueueReceiver get message = " + action);
    // vm 생성 로직


  }
}
