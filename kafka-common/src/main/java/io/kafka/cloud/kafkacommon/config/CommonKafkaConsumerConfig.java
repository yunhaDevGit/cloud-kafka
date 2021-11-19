package io.kafka.cloud.kafkacommon.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;

//public interface KafkaConsumerConfig {
//
//  Map<String, Object> consumerFactoryConfig(String groupId);
//
//  ConsumerFactory<String, Object> consumerFactory();
//
//  ConcurrentKafkaListenerContainerFactory<String, Object> vmKafkaListenerContainerFactory();
//}

//@Configuration
public class CommonKafkaConsumerConfig {

  @Value("${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  protected Map<String, Object> consumerFactoryConfig(String groupId) {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    return props;
  }

}