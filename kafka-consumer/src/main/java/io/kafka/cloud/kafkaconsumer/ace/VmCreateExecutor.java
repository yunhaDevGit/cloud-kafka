package io.kafka.cloud.kafkaconsumer.ace;

import io.kafka.cloud.kafkacommon.domain.Vm;
import io.kafka.cloud.kafkaconsumer.dto.VmDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class VmCreateExecutor {

  @KafkaListener(topics = "${kafka.topic.ace.vm}", groupId = "${kafka.group.ace.vm}", containerFactory = "vmKafkaListenerContainerFactory")
  public void listenWithHeaders(@Payload Vm vm, @Headers MessageHeaders messageHeaders) {
    System.out.println("Received Message: " + vm.toString() + " headers: " + messageHeaders);
  }
}
