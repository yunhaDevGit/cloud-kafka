package io.kafka.cloud.kafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"io.kafka.cloud.kafkacommon.config",
    "io.kafka.cloud.kafkaconsumer.ace"})
@SpringBootApplication
public class AceVirtdApplication {

  public static void main(String[] args) {
    SpringApplication.run(AceVirtdApplication.class, args);
  }

}
