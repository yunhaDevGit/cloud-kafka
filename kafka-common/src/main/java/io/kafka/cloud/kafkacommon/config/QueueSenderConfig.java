package io.kafka.cloud.kafkacommon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_CODE;
import io.kafka.cloud.kafkacommon.utils.kafkaqueue.QueueSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

//@RequiredArgsConstructor
//@Configuration
//public class QueueSenderConfig {
//
//  private final KafkaTemplate<String, String> kafkaTemplate;
//  private final ObjectMapper objectMapper;
//
//  @Bean
//  public QueueSender<ACTION_CODE> queueSender(){
//    return  new QueueSender<>(kafkaTemplate,objectMapper);
//  }
//}
