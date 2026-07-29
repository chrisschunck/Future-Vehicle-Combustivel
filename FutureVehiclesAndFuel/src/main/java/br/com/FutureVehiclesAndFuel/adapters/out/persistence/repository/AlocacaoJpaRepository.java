package br.com.FutureVehiclesAndFuel.adapters.out.persistence.repository;

import br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity.AlocacaoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlocacaoJpaRepository extends JpaRepository<AlocacaoJpaEntity, Long> {
    Optional<AlocacaoJpaEntity> findByVeiculoIdAndDataFimIsNull(Long veiculoId);
}
