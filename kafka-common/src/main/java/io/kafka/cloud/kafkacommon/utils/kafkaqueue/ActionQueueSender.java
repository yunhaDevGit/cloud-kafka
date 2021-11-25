package io.kafka.cloud.kafkacommon.utils.kafkaqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kafka.cloud.kafkacommon.utils.constant.ActionCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Configuration
public class ActionQueueSender<T extends ActionCode> {

  private static final Logger log = LoggerFactory.getLogger(ActionQueueSender.class);
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

//  public void sendSync(String topic, QueueAction<T> action)
//      throws JsonProcessingException, InterruptedException, ExecutionException, TimeoutException {
//    log.info("SendingMessage='{}', topic='{}'", action, topic);
//    String value = this.objectMapper.writeValueAsString(action);
//
//    try {
//      SendResult<String, String> result = this.kafkaTemplate.send(topic, value)
//          .get(1L, TimeUnit.SECONDS);
//    } catch (ExecutionException var6) {
//      log.error("ExecutionException Sending the Message and the exception is {}",
//          var6.getMessage());
//      throw var6;
//    } catch (InterruptedException | TimeoutException var7) {
//      log.error("TimeoutException/InterruptedException Sending the Message and the exception is {}",
//          var7.getMessage());
//      throw var7;
//    }
//  }

  public void sendAsync(String topic, QueueAction<T> action) throws JsonProcessingException {
    System.out.println("SendingMessage=" + action + ", topic=" + topic);
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
