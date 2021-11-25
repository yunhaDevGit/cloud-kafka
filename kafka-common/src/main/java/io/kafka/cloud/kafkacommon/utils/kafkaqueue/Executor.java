package io.kafka.cloud.kafkacommon.utils.kafkaqueue;

import io.kafka.cloud.kafkacommon.utils.constant.ActionCode;
import io.kafka.cloud.kafkacommon.utils.constant.ActionResult;

public interface Executor<K extends ActionCode> {

  Object execute(QueueAction<K> queueAction);

  ActionResult getResult();
}
