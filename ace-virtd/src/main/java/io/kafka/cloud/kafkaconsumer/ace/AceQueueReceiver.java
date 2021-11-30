package io.kafka.cloud.kafkaconsumer.ace;

import com.google.gson.Gson;
import io.kafka.cloud.kafkacommon.dto.VmDto;
import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_CODE;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@ComponentScan(basePackages = {"io.kafka.cloud.kafkacommon","io.kafka.cloud.kafkaconsumer.config"})
@Component
public class AceQueueReceiver {

  @Autowired
  private SendActionResult sendActionResult;

  @KafkaListener(topics = "${kafka.topic.ace}", groupId = "${kafka.consumer.group.ace}", containerFactory = "aceKafkaListenerContainerFactory")
  public void getAction(String action) {
    System.out.println("AceQueueReceiver - message = " + action);

    JSONObject actionObject = new JSONObject(action);
    JSONObject jsonObject = actionObject.optJSONObject("object");

    ACTION_CODE actionCode = ACTION_CODE.valueOf((String) actionObject.get("actionCode"));

    boolean result = true;
    switch (actionCode){
      case VM_CREATE:
        // vm 생성 로직

        Gson gson = new Gson();
        VmDto vmDto = gson.fromJson(jsonObject.toString(), VmDto.class);

        result = true;
        sendActionResult.sendMessage(vmDto, result);
        break;
      case VM_DELETE:
        // vm 삭제 로직
        break;
      default:
        // 위에 해당 하는 액션이 없는 경우
        break;
    }

  }

}
