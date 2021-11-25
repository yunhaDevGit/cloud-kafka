package io.kafka.cloud.kafkaproducer.receiver;

import io.kafka.cloud.kafkacommon.dto.ResultDto;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@ComponentScan(basePackages = "io.kafka.cloud.kafkacommon")
@Component
public class ResultReceiver {

  @KafkaListener(topics = "${kafka.topic.result}", groupId = "${kafka.consumer.group.result}", containerFactory = "resultKafkaListenerContainerFactory")
  public void getAction(@Payload ResultDto resultDto) {
    System.out.println("AceResultReceiver get message = " + resultDto);

  }
}
