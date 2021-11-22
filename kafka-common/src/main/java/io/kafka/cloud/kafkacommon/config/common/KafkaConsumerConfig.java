package io.kafka.cloud.kafkacommon.config.common;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public abstract class KafkaConsumerConfig<D> {

  @Value("${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  public Map<String, Object> consumerFactoryConfig(String groupId) {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    return props;
  }

  public abstract ConsumerFactory<String, D> consumerFactory();

  public abstract ConcurrentKafkaListenerContainerFactory<String, D> kafkaListenerContainerFactory();

}
