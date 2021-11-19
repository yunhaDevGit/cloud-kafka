package io.kafka.cloud.kafkaproducer.controller;

import io.kafka.cloud.kafkacommon.dto.VolumeDto;
import io.kafka.cloud.kafkaproducer.service.VolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VolumeController {

  @Autowired
  VolumeService volumeService;

  @PostMapping(value = "/volume/create")
  public String createVolume(@RequestBody VolumeDto volumeDto) {
    System.out.println("Volume Controller");
    return volumeService.createVolume(volumeDto);
  }
}
