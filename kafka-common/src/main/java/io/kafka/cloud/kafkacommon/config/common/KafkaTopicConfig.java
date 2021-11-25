package io.kafka.cloud.kafkacommon.config.common;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicConfig {

  @Value("${kafka.bootstrapAddress}")
  private String bootstrapServers;

  @Value("${kafka.topic.ace}")
  private String aceTopicName;


  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic aceTopic() {
    return TopicBuilder.name(aceTopicName)
        .partitions(1)
        .replicas(1)
        .build();
  }
}