package io.kafka.cloud.kafkacommon.config;

import io.kafka.cloud.kafkacommon.config.common.KafkaConsumerConfig;
import io.kafka.cloud.kafkacommon.dto.VmDto;
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
public class VmKafkaConsumerConfig extends KafkaConsumerConfig<VmDto> {

  @Value("${kafka.consumer.group.ace.vm}")
  private String vmGroupId;

  @Override
  public ConsumerFactory consumerFactory() {
    Map<String, Object> props = consumerFactoryConfig(vmGroupId);
    return new DefaultKafkaConsumerFactory<String, VmDto>(props,
        new StringDeserializer(),
        new ErrorHandlingDeserializer(new JsonDeserializer<>(VmDto.class)));
  }

  @Bean(name = "vmKafkaListenerContainerFactory")
  @Override
  public ConcurrentKafkaListenerContainerFactory<String, VmDto> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, VmDto> factory
        = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
//    factory.setConcurrency(3); -> Consumer 개수 설정 가능
    return factory;
  }
}
