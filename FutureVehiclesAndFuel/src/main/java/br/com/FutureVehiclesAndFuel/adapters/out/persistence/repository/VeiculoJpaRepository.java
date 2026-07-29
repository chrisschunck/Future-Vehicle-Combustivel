package br.com.FutureVehiclesAndFuel.adapters.out.persistence.repository;

import br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity.VeiculoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoJpaRepository extends JpaRepository<VeiculoJpaEntity, Long> {
}
