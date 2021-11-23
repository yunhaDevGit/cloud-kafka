package io.kafka.cloud.kafkacommon.utils;

import io.kafka.cloud.kafkacommon.utils.constant.ActionCode;
import io.kafka.cloud.kafkacommon.utils.kafkaqueue.Executor;

public class Constant {

  public static final String ACE = "ace";
  public static final String DNS = "dna";

  public static final String VM = "vm";
  public static final String VOLUME = "volume";
  public static final String SUBNET = "subnet";
  public static final String ADAPTIVE_IP = "adaptive_ip";


  public enum ACTION_CODE implements ActionCode {
    VM_LIST, VM_CREATE, VM_DELETE, VM_START;

    @Override
    public Executor getExecutor() {
      return null;
    }
  }

  public enum CMD_RESULT {
    SUCCESS, FAILED
  }
}
