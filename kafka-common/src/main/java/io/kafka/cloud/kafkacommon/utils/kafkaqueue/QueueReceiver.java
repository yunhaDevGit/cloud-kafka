package io.kafka.cloud.kafkacommon.utils.kafkaqueue;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
@Configuration
public interface QueueReceiver {

  void listen(String action) throws Exception;
}
