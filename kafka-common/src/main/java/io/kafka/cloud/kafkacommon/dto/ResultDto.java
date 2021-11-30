package io.kafka.cloud.kafkacommon.dto;

import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_CODE;
import io.kafka.cloud.kafkacommon.utils.Constant.ACTION_RESULT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto<T> {

  private T dto;
  private ACTION_CODE actionCode;
  private ACTION_RESULT action_result;

}
