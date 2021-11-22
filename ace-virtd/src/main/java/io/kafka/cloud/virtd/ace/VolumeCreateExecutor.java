package io.kafka.cloud.virtd.ace;

//@Component
//public class VolumeCreateExecutor {
//
//  @KafkaListener(topics = "${kafka.topic.ace.volume}", groupId = "${kafka.consumer.group.ace.volume}", containerFactory = "volumeKafkaListenerContainerFactory")
//  public void listenVolumeCreateMessage(@Payload VolumeDto volumeDto, @Headers MessageHeaders messageHeaders) {
//    System.out.println("Received Message: " + volumeDto.toString() + " headers: " + messageHeaders);
//    // volume 생성 로직
//  }
//}
