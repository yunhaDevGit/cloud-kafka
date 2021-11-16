package io.kafka.cloud.kafkaconsumer.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import io.kafka.cloud.kafkacommon.domain.Vm;
import lombok.Getter;
import lombok.Setter;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Getter
@Setter
public class VmDto {
  private String id;
  private String name;

  private int cpuNum;
  private long memSize;
  private int vncPort;

}
