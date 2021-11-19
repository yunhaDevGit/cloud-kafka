package io.kafka.cloud.kafkaproducer.config;

import io.kafka.cloud.kafkacommon.config.CommonKafkaProducerConfig;
import io.kafka.cloud.kafkacommon.dto.VmDto;
import io.kafka.cloud.kafkacommon.dto.VolumeDto;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig extends CommonKafkaProducerConfig {

  @Bean
  public ProducerFactory<String, VmDto> vmProducerFactory() {
    Map<String, Object> configProps = producerFactoryConfig();
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, VmDto> vmKafkaTemplate() {
    return new KafkaTemplate<>(vmProducerFactory());
  }

  @Bean
  public ProducerFactory<String, VolumeDto> volumeProducerFactory() {
    Map<String, Object> configProps = producerFactoryConfig();
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, VolumeDto> volumeKafkaTemplate() {
    return new KafkaTemplate<>(volumeProducerFactory());
  }
}
