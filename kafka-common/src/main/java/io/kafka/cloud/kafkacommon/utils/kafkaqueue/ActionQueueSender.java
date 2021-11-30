package io.kafka.cloud.kafkacommon.utils.kafkaqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kafka.cloud.kafkacommon.utils.constant.ActionCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Configuration
public class ActionQueueSender<T extends ActionCode> {
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

  public void sendAsync(String topic, QueueAction<T> action) throws JsonProcessingException {
    System.out.println("ActionQueueSender - sendAsync");
    System.out.println("SendingMessage=" + action.toString() + ", topic=" + topic);
    String value = this.objectMapper.writeValueAsString(action);
    ListenableFuture<SendResult<String, String>> listenableFuture = this.kafkaTemplate.send(topic,
        value);
    listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
      public void onSuccess(SendResult<String, String> result) {
        System.out.println("sendig success " + result);
      }

      public void onFailure(Throwable ex) {
        System.out.println("sending fail" + ex);
      }
    });
  }

  public ActionQueueSender(final KafkaTemplate<String, String> kafkaTemplate,
      final ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
  }
}
