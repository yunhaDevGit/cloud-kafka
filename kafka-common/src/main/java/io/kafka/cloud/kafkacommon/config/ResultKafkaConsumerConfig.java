package io.kafka.cloud.kafkacommon.config;

import io.kafka.cloud.kafkacommon.dto.ResultDto;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
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
public class ResultKafkaConsumerConfig {

  @Value("${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  @Value("${kafka.consumer.group.result}")
  private String resultGroupId;

  public Map<String, Object> consumerFactoryConfig(String groupId) {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
    return props;
  }

  public ConsumerFactory<String, ResultDto> consumerFactory() {
    return new DefaultKafkaConsumerFactory<String, ResultDto>(consumerFactoryConfig(resultGroupId),
        new StringDeserializer(),
        new ErrorHandlingDeserializer(new JsonDeserializer<>(ResultDto.class)));
  }

  @Bean(name = "resultKafkaListenerContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<String, ResultDto> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, ResultDto> factory
        = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
//    factory.setConcurrency(3); -> Consumer 개수 설정 가능
    return factory;
  }

}
