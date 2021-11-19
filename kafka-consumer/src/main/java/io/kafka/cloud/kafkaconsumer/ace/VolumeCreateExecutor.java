package io.kafka.cloud.kafkaconsumer.ace;

import io.kafka.cloud.kafkacommon.dto.VolumeDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class VolumeCreateExecutor {

  @KafkaListener(topics = "${kafka.topic.ace.volume}", groupId = "${kafka.consumer.group.ace.volume}", containerFactory = "volumeKafkaListenerContainerFactory")
  public void listenVolumeCreateMessage(@Payload VolumeDto volumeDto, @Headers MessageHeaders messageHeaders) {
    System.out.println("Received Message: " + volumeDto.toString() + " headers: " + messageHeaders);
    // volume 생성 로직
  }
}
