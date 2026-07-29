package br.com.FutureVehiclesAndFuel.application.service;

import br.com.FutureVehiclesAndFuel.application.port.in.AlocarVeiculoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.AlocarVeiculoCommand;
import br.com.FutureVehiclesAndFuel.application.port.out.AlocacaoRepositoryPort;
import br.com.FutureVehiclesAndFuel.application.port.out.MotoristaRepositoryPort;
import br.com.FutureVehiclesAndFuel.application.port.out.VeiculoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.exception.AlocacaoAtivaExistenteException;
import br.com.FutureVehiclesAndFuel.domain.exception.CnhVencidaException;
import br.com.FutureVehiclesAndFuel.domain.exception.EntidadeNaoEncontradaException;
import br.com.FutureVehiclesAndFuel.domain.model.Alocacao;
import br.com.FutureVehiclesAndFuel.domain.model.Motorista;
import br.com.FutureVehiclesAndFuel.domain.model.Veiculo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AlocarVeiculoService implements AlocarVeiculoUseCase {

    private final VeiculoRepositoryPort veiculoRepositoryPort;
    private final MotoristaRepositoryPort motoristaRepositoryPort;
    private final AlocacaoRepositoryPort alocacaoRepositoryPort;

    public AlocarVeiculoService(VeiculoRepositoryPort veiculoRepositoryPort,
                                 MotoristaRepositoryPort motoristaRepositoryPort,
                                 AlocacaoRepositoryPort alocacaoRepositoryPort) {
        this.veiculoRepositoryPort = veiculoRepositoryPort;
        this.motoristaRepositoryPort = motoristaRepositoryPort;
        this.alocacaoRepositoryPort = alocacaoRepositoryPort;
    }

    @Override
    @Transactional
    public Alocacao alocar(AlocarVeiculoCommand command) {
        Veiculo veiculo = veiculoRepositoryPort.buscarPorId(command.veiculoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Veiculo nao encontrado: id " + command.veiculoId()));

        Motorista motorista = motoristaRepositoryPort.buscarPorId(command.motoristaId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Motorista nao encontrado: id " + command.motoristaId()));

        if (!motorista.cnhValida()) {
            throw new CnhVencidaException(
                    "CNH do motorista " + motorista.getNome() + " esta vencida ou invalida");
        }

        // Regra: um veiculo so pode ter uma alocacao ativa por vez
        alocacaoRepositoryPort.buscarAlocacaoAtivaPorVeiculo(veiculo.getId()).ifPresent(a -> {
            throw new AlocacaoAtivaExistenteException(
                    "Veiculo de placa " + veiculo.getPlaca() + " ja possui uma alocacao ativa");
        });

        // marcarComoEmUso ja valida se o veiculo esta DISPONIVEL
        veiculo.marcarComoEmUso();
        veiculoRepositoryPort.salvar(veiculo);

        Alocacao alocacao = new Alocacao(
                null, veiculo.getId(), motorista.getId(), LocalDateTime.now(), null,
                command.kmInicial(), null);

        return alocacaoRepositoryPort.salvar(alocacao);
    }
}
