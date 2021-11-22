package io.kafka.cloud.admd.service;

//
//@Service
//public class VolumeService {
//
//  @Autowired
//  private KafkaTemplate<String, VolumeDto> volumeKafkaTemplate;
//
//  @Value("${kafka.topic.ace.volume}")
//  private String volumeTopicName;
//
//  public String createVolume(VolumeDto volumeDto) {
//
//    Message<VolumeDto> message = MessageBuilder
//        .withPayload(volumeDto)
//        .setHeader(KafkaHeaders.TOPIC, volumeTopicName)
//        .build();
//
//    ListenableFuture<SendResult<String, VolumeDto>> future =
//        volumeKafkaTemplate.send(message);
//
//    future.addCallback(new ListenableFutureCallback<SendResult<String, VolumeDto>>() {
//
//      @Override
//      public void onSuccess(SendResult<String, VolumeDto> stringObjectSendResult) {
//        System.out.println("Sent message=[" + stringObjectSendResult.getProducerRecord().value().toString() +
//            "] with offset=[" + stringObjectSendResult.getRecordMetadata().offset() + "]");
//      }
//
//      @Override
//      public void onFailure(Throwable ex) {
//        System.out.println("Unable to send message=[] due to : " + ex.getMessage());
//      }
//    });
//
//    return "success";
//  }
//}
