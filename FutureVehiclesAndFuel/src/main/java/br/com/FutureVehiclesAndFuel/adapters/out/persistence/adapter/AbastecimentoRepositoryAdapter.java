package br.com.FutureVehiclesAndFuel.adapters.out.persistence.adapter;

import br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity.AbastecimentoJpaEntity;
import br.com.FutureVehiclesAndFuel.adapters.out.persistence.repository.AbastecimentoJpaRepository;
import br.com.FutureVehiclesAndFuel.application.port.out.AbastecimentoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.model.Abastecimento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AbastecimentoRepositoryAdapter implements AbastecimentoRepositoryPort {

    private final AbastecimentoJpaRepository jpaRepository;

    public AbastecimentoRepositoryAdapter(AbastecimentoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Abastecimento salvar(Abastecimento abastecimento) {
        AbastecimentoJpaEntity entity = new AbastecimentoJpaEntity(
                abastecimento.getId(), abastecimento.getVeiculoId(), abastecimento.getDataHora(),
                abastecimento.getQuantidade(), abastecimento.getCusto(), abastecimento.getLocalEletroposto());
        AbastecimentoJpaEntity salvo = jpaRepository.save(entity);
        return toDomain(salvo);
    }

    @Override
    public List<Abastecimento> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private Abastecimento toDomain(AbastecimentoJpaEntity entity) {
        return new Abastecimento(entity.getId(), entity.getVeiculoId(), entity.getDataHora(),
                entity.getQuantidade(), entity.getCusto(), entity.getLocalEletroposto());
    }
}
