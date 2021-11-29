package io.kafka.cloud.kafkaproducer.controller;

import io.kafka.cloud.kafkacommon.dto.VmDto;
import io.kafka.cloud.kafkaproducer.service.VmService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    String id = UUID.randomUUID().toString();
    vmDto.setId(id);
    return vmService.createVm(vmDto);
  }

  @DeleteMapping("/vm/delete/{id}")
  public String deleteVm(@PathVariable String id) {
    System.out.println("VmController - deleteVm");
    return vmService.deleteVm(id);
  }
}
