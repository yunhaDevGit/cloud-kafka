package io.kafka.cloud.kafkaproducer.service;

import io.kafka.cloud.kafkacommon.dto.VolumeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class VolumeService {

  @Autowired
  private KafkaTemplate<String, VolumeDto> volumeKafkaTemplate;

  @Value("${kafka.topic.ace.volume}")
  private String volumeTopicName;

  public String createVolume(VolumeDto volumeDto) {

    Message<VolumeDto> message = MessageBuilder
        .withPayload(volumeDto)
        .setHeader(KafkaHeaders.TOPIC, volumeTopicName)
        .build();

    ListenableFuture<SendResult<String, VolumeDto>> future =
        volumeKafkaTemplate.send(message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, VolumeDto>>() {

      @Override
      public void onSuccess(SendResult<String, VolumeDto> stringObjectSendResult) {
        System.out.println("Sent message=[" + stringObjectSendResult.getProducerRecord().value().toString() +
            "] with offset=[" + stringObjectSendResult.getRecordMetadata().offset() + "]");
      }

      @Override
      public void onFailure(Throwable ex) {
        System.out.println("Unable to send message=[] due to : " + ex.getMessage());
      }
    });

    return "success";
  }
}
