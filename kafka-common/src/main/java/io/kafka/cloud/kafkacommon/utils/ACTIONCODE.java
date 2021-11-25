package io.kafka.cloud.kafkacommon.utils;

import io.kafka.cloud.kafkacommon.utils.constant.Constant;

public enum ACTIONCODE implements Constant {

  VM_CREATE("VM_CREATE"),
  VM_LIST("VM_LIST"),
  VM_DELETE("VM_DELETE"),
  VM_START("VM_START");

  private final String actioncode;

  ACTIONCODE(String actioncode) {
    this.actioncode = actioncode;
  }

  public String getState() {
    return actioncode;
  }

  @Override
  public Object getExecutor() {
    return null;
  }

}
