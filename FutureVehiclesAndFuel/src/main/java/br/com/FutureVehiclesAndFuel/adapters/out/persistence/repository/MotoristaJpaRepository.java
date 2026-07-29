package br.com.FutureVehiclesAndFuel.adapters.out.persistence.repository;

import br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity.MotoristaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoristaJpaRepository extends JpaRepository<MotoristaJpaEntity, Long> {
}
