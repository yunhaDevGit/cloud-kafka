package io.kafka.cloud.kafkacommon.mapper;

import io.kafka.cloud.kafkacommon.domain.Vm;
import io.kafka.cloud.kafkacommon.dto.VmDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VmMapper extends GenericMapper<VmDto, Vm> {

}
