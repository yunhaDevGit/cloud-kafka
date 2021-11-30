package io.kafka.cloud.kafkacommon.utils;

public class Constant {

  public static final String ACE = "ace";
  public static final String DNA = "dna";

  public static final String VM = "vm";
  public static final String VOLUME = "volume";
  public static final String SUBNET = "subnet";
  public static final String ADAPTIVE_IP = "adaptive_ip";


  public enum ACTION_CODE {
    VM_LIST, VM_CREATE, VM_DELETE, VM_START;
  }

  public enum ACTION_RESULT {
    ACTION_SUCCESS, ACTION_FAILED
  }
}
