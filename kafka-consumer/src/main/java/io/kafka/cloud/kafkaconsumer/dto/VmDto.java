package io.kafka.cloud.kafkaconsumer.dto;

import io.kafka.cloud.kafkacommon.domain.Vm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VmDto {
  private String id;
  private String name;

  private int cpuNum;
  private long memSize;
  private int vncPort;

}
