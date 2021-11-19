package io.kafka.cloud.kafkaconsumer.config;

import io.kafka.cloud.kafkacommon.config.CommonKafkaConsumerConfig;
import io.kafka.cloud.kafkacommon.dto.VmDto;
import io.kafka.cloud.kafkacommon.dto.VolumeDto;
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
public class KafkaConsumerConfig extends CommonKafkaConsumerConfig {

  @Value("${kafka.consumer.group.ace.vm}")
  private String vmGroupId;

  @Value("${kafka.consumer.group.ace.volume}")
  private String volumeGroupId;

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
//    factory.setConcurrency(3); -> Consumer 개수 설정 가능
    return factory;
  }

  public ConsumerFactory<String, VolumeDto> volumeConsumerFactory() {
    Map<String, Object> props = consumerFactoryConfig(vmGroupId);
    return new DefaultKafkaConsumerFactory<>(props,
        new StringDeserializer(),
        new ErrorHandlingDeserializer(new JsonDeserializer<>(VolumeDto.class)));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, VolumeDto> volumeKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, VolumeDto> factory
        = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(volumeConsumerFactory());
    return factory;
  }

}
