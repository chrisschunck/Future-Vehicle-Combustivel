package br.com.FutureVehiclesAndFuel.adapters.out.persistence.adapter;

import br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity.ManutencaoJpaEntity;
import br.com.FutureVehiclesAndFuel.adapters.out.persistence.repository.ManutencaoJpaRepository;
import br.com.FutureVehiclesAndFuel.application.port.out.ManutencaoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.model.Manutencao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManutencaoRepositoryAdapter implements ManutencaoRepositoryPort {

    private final ManutencaoJpaRepository jpaRepository;

    public ManutencaoRepositoryAdapter(ManutencaoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Manutencao salvar(Manutencao manutencao) {
        ManutencaoJpaEntity entity = new ManutencaoJpaEntity(
                manutencao.getId(), manutencao.getVeiculoId(), manutencao.getTipo(),
                manutencao.getQuilometragemRealizada(), manutencao.getDataRealizada(),
                manutencao.getIntervaloProximaKm(), manutencao.getCusto());
        ManutencaoJpaEntity salvo = jpaRepository.save(entity);
        return toDomain(salvo);
    }

    @Override
    public List<Manutencao> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private Manutencao toDomain(ManutencaoJpaEntity entity) {
        return new Manutencao(entity.getId(), entity.getVeiculoId(), entity.getTipo(),
                entity.getQuilometragemRealizada(), entity.getDataRealizada(),
                entity.getIntervaloProximaKm(), entity.getCusto());
    }
}
