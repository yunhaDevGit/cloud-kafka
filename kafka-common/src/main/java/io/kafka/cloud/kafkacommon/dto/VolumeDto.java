package io.kafka.cloud.kafkacommon.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VolumeDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;
  private String name;
  private int size;
}
