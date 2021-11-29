package io.kafka.cloud.kafkacommon.utils.kafkaqueue;

import io.kafka.cloud.kafkacommon.utils.constant.ActionCode;

public class QueueAction<K extends ActionCode> {

  private static final long serialVersionUID = 1L;
  private String actionId;
  private Object object;
  private K actionCode;

  public static <K extends ActionCode> QueueAction.QueueActionBuilder<K> builder() {
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

  public K getActionCode() {
    return actionCode;
  }

  public void setActionCode(final K actionCode) {
    this.actionCode = actionCode;
  }

  public QueueAction(final String actionId, final Object object,
      final K actionCode) {
    this.actionId = actionId;
    this.object = object;
    this.actionCode = actionCode;
  }

  public static class QueueActionBuilder<K extends ActionCode> {

    private String actionId;
    private Object object;
    private K actionCode;

    QueueActionBuilder() {

    }

    public QueueAction.QueueActionBuilder<K> actionId(final String actionId) {
      this.actionId = actionId;
      return this;
    }

    public QueueAction.QueueActionBuilder<K> object(final Object object) {
      this.object = object;
      return this;
    }

    public QueueAction.QueueActionBuilder<K> actionCode(final K actionCode) {
      this.actionCode = actionCode;
      return this;
    }

    public QueueAction<K> build() {
      return new QueueAction(this.actionId, this.object, this.actionCode);
    }

    public String toString() {
      return "QueueAction.QueueActionBuilder(actionId=" + this.actionId + ", object=" + this.object + ", actionCode=" + this.actionCode;
    }
  }
}

