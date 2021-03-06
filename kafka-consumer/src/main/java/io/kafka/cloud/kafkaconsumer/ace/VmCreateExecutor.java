package io.kafka.cloud.kafkaconsumer.ace;

import io.kafka.cloud.kafkacommon.dto.VmDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class VmCreateExecutor {

  @KafkaListener(topics = "${kafka.topic.ace.vm}", groupId = "${kafka.consumer.group.ace.vm}", containerFactory = "vmKafkaListenerContainerFactory")
  public void listenVmCreateMessage(@Payload VmDto vmDto, @Headers MessageHeaders messageHeaders) {
    System.out.println("Received Message: " + vmDto.toString() + " headers: " + messageHeaders);
    // vm 생성 로직
  }
}
