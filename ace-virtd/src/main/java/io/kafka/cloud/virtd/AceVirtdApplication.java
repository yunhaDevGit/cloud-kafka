package io.kafka.cloud.virtd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"io.kafka.cloud.kafkacommon.config", "io.kafka.cloud.virtd.ace"})
@SpringBootApplication
public class AceVirtdApplication {

  public static void main(String[] args) {
    SpringApplication.run(AceVirtdApplication.class, args);
  }

}
