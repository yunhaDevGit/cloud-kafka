package io.kafka.cloud.kafkaconsumer.config;

import io.kafka.cloud.kafkacommon.config.KafkaConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class AceKafkaConsumerConfig extends KafkaConsumerConfig {

  @Value("${kafka.consumer.group.ace}")
  private String aceGroupId;

  @Override
  public ConsumerFactory<String, Object> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerFactoryConfig(aceGroupId),
        new StringDeserializer(),
        new ErrorHandlingDeserializer(new JsonDeserializer<>()));
  }

  @Bean(name = "aceKafkaListenerContainerFactory")
  @Override
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
//    factory.setConcurrency(3); -> Consumer 개수 설정 가능
    return factory;
  }

}
