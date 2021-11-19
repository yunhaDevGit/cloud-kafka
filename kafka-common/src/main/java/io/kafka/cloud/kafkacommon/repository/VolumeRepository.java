package io.kafka.cloud.kafkacommon.repository;

import io.kafka.cloud.kafkacommon.domain.Volume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolumeRepository extends JpaRepository<Volume, String> {

}
