package br.com.FutureVehiclesAndFuel.adapters.out.persistence.repository;

import br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity.AbastecimentoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbastecimentoJpaRepository extends JpaRepository<AbastecimentoJpaEntity, Long> {
}
