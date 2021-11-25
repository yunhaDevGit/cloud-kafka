package io.kafka.cloud.kafkacommon.dto;

import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_RESULT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto<T,K> {

  private T dto;
  private K action_result;

}
