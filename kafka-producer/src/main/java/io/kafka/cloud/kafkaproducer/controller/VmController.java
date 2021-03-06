package io.kafka.cloud.kafkaproducer.controller;

import io.kafka.cloud.kafkacommon.dto.VmDto;
import io.kafka.cloud.kafkaproducer.service.VmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VmController {

  @Autowired
  VmService vmService;

  @PostMapping("/vm/create")
  public String createVm(@RequestBody VmDto vmDto) {
    System.out.println("VmController - createVm");
    return vmService.createVm(vmDto);
  }
}
