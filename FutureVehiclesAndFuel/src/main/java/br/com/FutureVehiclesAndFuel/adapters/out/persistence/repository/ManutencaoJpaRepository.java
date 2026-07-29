package br.com.FutureVehiclesAndFuel.adapters.out.persistence.repository;

import br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity.ManutencaoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManutencaoJpaRepository extends JpaRepository<ManutencaoJpaEntity, Long> {
}
