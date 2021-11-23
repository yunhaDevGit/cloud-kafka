package io.kafka.cloud.kafkacommon.utils.queue;

import io.kafka.cloud.kafkacommon.utils.kafkaqueue.QueueAction;
import org.apache.kafka.clients.producer.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;

public class QueueSender<T extends QueueAction<?>>{

  private static final Logger LOG = LoggerFactory.getLogger(QueueSender.class);

  @Autowired
  private KafkaTemplate<String, T> kafkaTemplate;


  public void send(String topic, T action){
    LOG.info("sending message='{}' to topic='{}'", action, topic);
    String className = action.getObject().getClass().getSimpleName();
    LOG.info("topic='{}'",className);
    kafkaTemplate.send(className, action);
  }

  @Async
  public void send(String topic, QueueAction action, Callback callback){
    LOG.info("sending message='{}' to topic='{}'", action, topic);
  }

}
