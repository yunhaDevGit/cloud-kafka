package io.kafka.cloud.kafkaconsumer.ace;

import io.kafka.cloud.kafkaconsumer.dto.VmDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class VmCreateExecutor {

  @KafkaListener(topics = "${kafka.vm.topic.name}", groupId = "${kafka.vm.topic.group.name}", containerFactory = "vmKafkaListenerContainerFactory")
  public void listenWithHeaders(@Payload VmDto vmDto, @Headers MessageHeaders messageHeaders) {
    System.out.println("Received Message: " + vmDto.toString() + " headers: " + messageHeaders);
  }
}
