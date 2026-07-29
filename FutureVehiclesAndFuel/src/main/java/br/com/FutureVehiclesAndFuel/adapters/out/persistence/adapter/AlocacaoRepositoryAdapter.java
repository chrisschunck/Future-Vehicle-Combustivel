package br.com.FutureVehiclesAndFuel.adapters.out.persistence.adapter;

import br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity.AlocacaoJpaEntity;
import br.com.FutureVehiclesAndFuel.adapters.out.persistence.repository.AlocacaoJpaRepository;
import br.com.FutureVehiclesAndFuel.application.port.out.AlocacaoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.model.Alocacao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AlocacaoRepositoryAdapter implements AlocacaoRepositoryPort {

    private final AlocacaoJpaRepository jpaRepository;

    public AlocacaoRepositoryAdapter(AlocacaoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Alocacao salvar(Alocacao alocacao) {
        AlocacaoJpaEntity entity = new AlocacaoJpaEntity(
                alocacao.getId(), alocacao.getVeiculoId(), alocacao.getMotoristaId(),
                alocacao.getDataInicio(), alocacao.getDataFim(), alocacao.getKmInicial(), alocacao.getKmFinal());
        AlocacaoJpaEntity salvo = jpaRepository.save(entity);
        return toDomain(salvo);
    }

    @Override
    public Optional<Alocacao> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Alocacao> buscarAlocacaoAtivaPorVeiculo(Long veiculoId) {
        return jpaRepository.findByVeiculoIdAndDataFimIsNull(veiculoId).map(this::toDomain);
    }

    @Override
    public List<Alocacao> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private Alocacao toDomain(AlocacaoJpaEntity entity) {
        return new Alocacao(entity.getId(), entity.getVeiculoId(), entity.getMotoristaId(),
                entity.getDataInicio(), entity.getDataFim(), entity.getKmInicial(), entity.getKmFinal());
    }
}
