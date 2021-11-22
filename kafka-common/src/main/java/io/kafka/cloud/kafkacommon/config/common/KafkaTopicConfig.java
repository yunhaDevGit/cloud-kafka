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

  @Value("${kafka.topic.ace.vm}")
  private String vmTopicName;

  @Value("${kafka.topic.ace.volume}")
  private String volumeTopicName;

  @Value("${kafka.topic.ace.result}")
  private String resultTopicName;

  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic vmTopic() {
    return TopicBuilder.name(vmTopicName)
        .partitions(1)
        .replicas(1)
        .build();
  }

  @Bean
  public NewTopic volumeTopic() {
    return TopicBuilder.name(volumeTopicName)
        .partitions(1)
        .replicas(1)
        .build();
  }

  @Bean
  public NewTopic vmResultTopic() {
    return TopicBuilder.name(resultTopicName)
        .partitions(1)
        .replicas(1)
        .build();
  }
}