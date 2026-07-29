package br.com.FutureVehiclesAndFuel.application.service;

import br.com.FutureVehiclesAndFuel.application.port.in.FinalizarAlocacaoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.out.AlocacaoRepositoryPort;
import br.com.FutureVehiclesAndFuel.application.port.out.VeiculoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.exception.EntidadeNaoEncontradaException;
import br.com.FutureVehiclesAndFuel.domain.model.Alocacao;
import br.com.FutureVehiclesAndFuel.domain.model.Veiculo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FinalizarAlocacaoService implements FinalizarAlocacaoUseCase {

    private final AlocacaoRepositoryPort alocacaoRepositoryPort;
    private final VeiculoRepositoryPort veiculoRepositoryPort;

    public FinalizarAlocacaoService(AlocacaoRepositoryPort alocacaoRepositoryPort,
                                     VeiculoRepositoryPort veiculoRepositoryPort) {
        this.alocacaoRepositoryPort = alocacaoRepositoryPort;
        this.veiculoRepositoryPort = veiculoRepositoryPort;
    }

    @Override
    @Transactional
    public Alocacao finalizar(Long alocacaoId, Integer kmFinal) {
        Alocacao alocacao = alocacaoRepositoryPort.buscarPorId(alocacaoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Alocacao nao encontrada: id " + alocacaoId));

        alocacao.finalizar(kmFinal);
        Alocacao alocacaoFinalizada = alocacaoRepositoryPort.salvar(alocacao);

        Veiculo veiculo = veiculoRepositoryPort.buscarPorId(alocacao.getVeiculoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Veiculo nao encontrado: id " + alocacao.getVeiculoId()));
        veiculo.marcarComoDisponivel();
        if (kmFinal != null) {
            veiculo.atualizarQuilometragem(kmFinal);
        }
        veiculoRepositoryPort.salvar(veiculo);

        return alocacaoFinalizada;
    }
}
