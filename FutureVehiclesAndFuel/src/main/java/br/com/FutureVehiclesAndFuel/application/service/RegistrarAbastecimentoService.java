package br.com.FutureVehiclesAndFuel.application.service;

import br.com.FutureVehiclesAndFuel.application.port.in.RegistrarAbastecimentoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.RegistrarAbastecimentoCommand;
import br.com.FutureVehiclesAndFuel.application.port.out.AbastecimentoRepositoryPort;
import br.com.FutureVehiclesAndFuel.application.port.out.VeiculoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.exception.EntidadeNaoEncontradaException;
import br.com.FutureVehiclesAndFuel.domain.model.Abastecimento;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrarAbastecimentoService implements RegistrarAbastecimentoUseCase {

    private final AbastecimentoRepositoryPort abastecimentoRepositoryPort;
    private final VeiculoRepositoryPort veiculoRepositoryPort;

    public RegistrarAbastecimentoService(AbastecimentoRepositoryPort abastecimentoRepositoryPort,
                                          VeiculoRepositoryPort veiculoRepositoryPort) {
        this.abastecimentoRepositoryPort = abastecimentoRepositoryPort;
        this.veiculoRepositoryPort = veiculoRepositoryPort;
    }

    @Override
    public Abastecimento registrar(RegistrarAbastecimentoCommand command) {
        veiculoRepositoryPort.buscarPorId(command.veiculoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Veiculo nao encontrado: id " + command.veiculoId()));

        Abastecimento abastecimento = new Abastecimento(
                null, command.veiculoId(), LocalDateTime.now(), command.quantidade(),
                command.custo(), command.localEletroposto());

        return abastecimentoRepositoryPort.salvar(abastecimento);
    }
}
