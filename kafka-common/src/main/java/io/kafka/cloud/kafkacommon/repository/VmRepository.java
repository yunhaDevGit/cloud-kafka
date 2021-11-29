package io.kafka.cloud.kafkacommon.repository;

import io.kafka.cloud.kafkacommon.domain.Vm;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VmRepository extends JpaRepository<Vm, String> {

  void deleteById(String id);

  Optional<Vm> findById(String id);
}
