package io.kafka.cloud.kafkacommon.mapper;

import io.kafka.cloud.kafkacommon.domain.Volume;
import io.kafka.cloud.kafkacommon.dto.VolumeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VolumeMapper extends GenericMapper<VolumeDto, Volume> {

}
