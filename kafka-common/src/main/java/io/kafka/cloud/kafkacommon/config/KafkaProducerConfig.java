package io.kafka.cloud.kafkacommon.config;

import io.kafka.cloud.kafkacommon.utils.kafkaqueue.QueueAction;
import io.kafka.cloud.kafkacommon.utils.queue.QueueSender;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig<T extends QueueAction<?>> {

  @Value("${kafka.bootstrapAddress}")
  private String bootstrapServers;

  public Map<String, Object> producerFactoryConfig() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return configProps;
  }

  public ProducerFactory<String, T> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerFactoryConfig());
  }

  @Bean
  public KafkaTemplate<String, T> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public QueueSender<T> sender() {
    return new QueueSender();
  }
}
