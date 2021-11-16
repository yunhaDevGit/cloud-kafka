package io.kafka.cloud.kafkaproducer.dto;

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

//  public Vm toEntity() {
//    return Vm.builder()
//        .id(this.id)
//        .name(this.name)
//        .cpuNum(this.cpuNum)
//        .memSize(this.memSize)
//        .vncPort(this.vncPort)
//        .build();
//  }
}
