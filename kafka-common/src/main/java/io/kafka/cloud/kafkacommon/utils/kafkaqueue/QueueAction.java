package io.kafka.cloud.kafkacommon.utils.kafkaqueue;

import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_CODE;

public class QueueAction{

  private String actionId;
  private Object object;
  private ACTION_CODE actionCode;

  public static QueueAction.QueueActionBuilder builder() {
    return new QueueAction.QueueActionBuilder();
  }

  public String getActionId() {
    return actionId;
  }

  public void setActionId(final String actionId) {
    this.actionId = actionId;
  }


  public Object getObject() {
    return object;
  }

  public void setObject(final Object object) {
    this.object = object;
  }

  public ACTION_CODE getActionCode() {
    return actionCode;
  }

  public void setActionCode(final ACTION_CODE actionCode) {
    this.actionCode = actionCode;
  }

  public QueueAction(final String actionId, final Object object,
      final ACTION_CODE actionCode) {
    this.actionId = actionId;
    this.object = object;
    this.actionCode = actionCode;
  }

  public static class QueueActionBuilder {

    private String actionId;
    private Object object;
    private ACTION_CODE actionCode;

    QueueActionBuilder() {

    }

    public QueueAction.QueueActionBuilder actionId(final String actionId) {
      this.actionId = actionId;
      return this;
    }

    public QueueAction.QueueActionBuilder object(final Object object) {
      this.object = object;
      return this;
    }

    public QueueAction.QueueActionBuilder actionCode(final ACTION_CODE actionCode) {
      this.actionCode = actionCode;
      return this;
    }

    public QueueAction build() {
      return new QueueAction(this.actionId, this.object, this.actionCode);
    }

    public String toString() {
      return "QueueAction.QueueActionBuilder(actionId=" + this.actionId + ", object=" + this.object + ", actionCode=" + this.actionCode;
    }
  }
}

