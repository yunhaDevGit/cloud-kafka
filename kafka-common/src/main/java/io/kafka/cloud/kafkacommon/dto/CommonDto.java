package io.kafka.cloud.kafkacommon.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.io.Serializable;
import lombok.Setter;
import lombok.ToString;

@JsonAutoDetect
@Setter
@ToString
public class CommonDto implements Serializable {

  private int num;

}
