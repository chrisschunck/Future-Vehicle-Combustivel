package br.com.FutureVehiclesAndFuel.application.port.out;

import br.com.FutureVehiclesAndFuel.domain.model.Alocacao;

import java.util.List;
import java.util.Optional;

public interface AlocacaoRepositoryPort {
    Alocacao salvar(Alocacao alocacao);
    Optional<Alocacao> buscarPorId(Long id);
    Optional<Alocacao> buscarAlocacaoAtivaPorVeiculo(Long veiculoId);
    List<Alocacao> listarTodos();
}
