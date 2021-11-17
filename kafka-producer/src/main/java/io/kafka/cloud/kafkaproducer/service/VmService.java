package io.kafka.cloud.kafkaproducer.service;

import io.kafka.cloud.kafkacommon.domain.Vm;
import io.kafka.cloud.kafkacommon.repository.VmRepository;
import io.kafka.cloud.kafkaproducer.dto.VmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@ComponentScan(basePackages = "io.kafka.cloud.kafkacommon")
@Service
public class VmService {

  @Autowired
  private KafkaTemplate<String, Vm> vmKafkaTemplate;

  @Autowired
  VmRepository vmRepository;

  @Value("${kafka.topic.ace.vm}")
  private String vmTopicName;

  @Transactional
  public String createVm(Vm vm) {

    // db에 넣는 로직
    System.out.println("VmService - createVm");

    Message<Vm> message = MessageBuilder
        .withPayload(vm)
        .setHeader(KafkaHeaders.TOPIC, vmTopicName)
        .build();

    ListenableFuture<SendResult<String, Vm>> future =
        vmKafkaTemplate.send(message);

    future.addCallback(new ListenableFutureCallback<SendResult<String, Vm>>() {

      @Override
      public void onSuccess(SendResult<String, Vm> stringObjectSendResult) {
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
