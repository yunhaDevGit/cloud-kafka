package io.kafka.cloud.kafkacommon.config.common;

import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class ResultKafkaProducerConfig  extends KafkaProducerConfig {

  @Bean(name = "resultProducerFactory")
  @Override
  public ProducerFactory<String, String> producerFactory() {
    Map<String, Object> configProps = producerFactoryConfig();
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean(name = "resultKafkaTemplate")
  @Override
  public KafkaTemplate kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
