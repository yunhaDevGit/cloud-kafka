package io.kafka.cloud.kafkacommon.config;

import io.kafka.cloud.kafkacommon.config.common.KafkaProducerConfig;
import io.kafka.cloud.kafkacommon.dto.VmDto;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class VmKafkaProducerConfig extends KafkaProducerConfig {

  @Bean(name = "vmProducerFactory")
  @Override
  public ProducerFactory<String, VmDto> producerFactory() {
    Map<String, Object> configProps = producerFactoryConfig();
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean(name = "vmKafkaTemplate")
  @Override
  public KafkaTemplate kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
