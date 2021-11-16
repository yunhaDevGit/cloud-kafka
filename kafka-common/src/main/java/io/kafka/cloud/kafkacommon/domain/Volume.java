package io.kafka.cloud.kafkacommon.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volume {

  @Id
  @GeneratedValue(generator = "uuid")
  private String id;
  private String name;
  private int size;
}
