package io.kafka.cloud.kafkacommon.utils;

import io.kafka.cloud.kafkacommon.utils.constant.ActionCode;
import io.kafka.cloud.kafkacommon.utils.constant.ActionResult;

public class Constant {

  public static final String ACE = "ace";
  public static final String DNS = "dna";

  public static final String VM = "vm";
  public static final String VOLUME = "volume";
  public static final String SUBNET = "subnet";
  public static final String ADAPTIVE_IP = "adaptive_ip";


  public enum ACTION_CODE implements ActionCode {
    VM_LIST, VM_CREATE, VM_DELETE, VM_START;
  }

  public enum ACTION_RESULT implements ActionResult {
    ACTION_SUCCESS, ACTION_FAILED
  }
}
