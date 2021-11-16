package io.kafka.cloud.kafkacommon.repository;

import io.kafka.cloud.kafkacommon.domain.Vm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VmRepository extends JpaRepository<Vm, String> {
}
