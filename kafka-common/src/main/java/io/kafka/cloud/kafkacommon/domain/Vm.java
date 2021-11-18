package io.kafka.cloud.kafkacommon.domain;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vm {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  @Column(columnDefinition = "VARCHAR(36)", insertable = false, updatable = false, nullable = false)
  private String id;

  private String name;
  private int cpuNum;
  private long memSize;
  private int vncPort;

//  public String getId() {
//    return id;
//  }
//
//  public String getName() {
//    return name;
//  }
//
//  public int getCpuNum() {
//    return cpuNum;
//  }
//
//  public long getMemSize() {
//    return memSize;
//  }
//
//  public int getVncPort() {
//    return vncPort;
//  }
}
