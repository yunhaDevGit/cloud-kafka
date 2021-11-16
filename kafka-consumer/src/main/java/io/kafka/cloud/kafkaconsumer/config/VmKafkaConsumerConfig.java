package io.kafka.cloud.kafkaconsumer.config;

import io.kafka.cloud.kafkacommon.config.KafkaConsumerConfig;
import io.kafka.cloud.kafkaconsumer.dto.VmDto;
import java.util.Map;
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
public class VmKafkaConsumerConfig extends KafkaConsumerConfig {

  @Value("${kafka.group.ace.vm}")
  private String vmGroupId;

  public ConsumerFactory<String, VmDto> vmConsumerFactory() {
    Map<String, Object> props = consumerFactoryConfig(vmGroupId);
    return new DefaultKafkaConsumerFactory<>(props,
        new StringDeserializer(),
        new ErrorHandlingDeserializer(new JsonDeserializer<>(VmDto.class)));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, VmDto> vmKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, VmDto> factory
        = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(vmConsumerFactory());
    return factory;
  }

}
