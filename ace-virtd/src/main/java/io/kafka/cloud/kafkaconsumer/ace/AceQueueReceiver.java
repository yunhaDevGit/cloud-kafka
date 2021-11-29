package io.kafka.cloud.kafkaconsumer.ace;

import com.google.gson.Gson;
import io.kafka.cloud.kafkacommon.domain.Vm;
import io.kafka.cloud.kafkacommon.dto.ResultDto;
import io.kafka.cloud.kafkacommon.dto.VmDto;
import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_CODE;
import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_RESULT;
import io.kafka.cloud.kafkacommon.utils.constant.ActionCode;
import io.kafka.cloud.kafkacommon.utils.constant.ActionResult;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@ComponentScan(basePackages = "io.kafka.cloud.kafkacommon")
@Component
public class AceQueueReceiver {

  @Value("${kafka.topic.result}")
  private String actionResultTopic;

  @Autowired
  private KafkaTemplate<String, ResultDto> resultKafkaTemplate;

  @Autowired
  private SendActionResult sendActionResult;

  @KafkaListener(topics = "${kafka.topic.ace}", groupId = "${kafka.consumer.group.ace}", containerFactory = "aceKafkaListenerContainerFactory")
  public void getAction(String action) {
    System.out.println("AceQueueReceiver get message = " + action);

    JSONObject actionObject = new JSONObject(action);
    JSONObject jsonObject = actionObject.optJSONObject("object");
//    ACTION_CODE actionCode = (ACTION_CODE) actionObject.get("actionCode");

    ACTION_CODE actionCode = ACTION_CODE.valueOf((String) actionObject.get("actionCode"));

    boolean result = true;
    switch (actionCode){
      case VM_CREATE:
        // vm 생성 로직

        Gson gson = new Gson();
        VmDto vmDto = gson.fromJson(jsonObject.toString(), VmDto.class);

        result = true;
//        SendActionResult<VmDto> sendActionResult = null;
        sendActionResult.sendMessage(vmDto, result);
        break;
      case VM_DELETE:
        // vm 삭제 로직
        // result= true;
        break;
      default:
        // 해당 하는 액션이 없는 경우
        break;
    }

  }

}
