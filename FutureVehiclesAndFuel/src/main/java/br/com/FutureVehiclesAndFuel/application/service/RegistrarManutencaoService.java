package br.com.FutureVehiclesAndFuel.application.service;

import br.com.FutureVehiclesAndFuel.application.port.in.RegistrarManutencaoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.RegistrarManutencaoCommand;
import br.com.FutureVehiclesAndFuel.application.port.out.ManutencaoRepositoryPort;
import br.com.FutureVehiclesAndFuel.application.port.out.VeiculoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.exception.EntidadeNaoEncontradaException;
import br.com.FutureVehiclesAndFuel.domain.model.Manutencao;
import br.com.FutureVehiclesAndFuel.domain.model.Veiculo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Regra de negocio: ao registrar uma manutencao, o veiculo e marcado como
 * DISPONIVEL novamente (assume-se que a manutencao ja foi concluida no
 * momento do registro; nao ha fluxo de "em manutencao" em aberto neste escopo).
 * Caso o cadastro represente o INICIO da manutencao, use veiculo.marcarComoEmManutencao()
 * em um caso de uso separado antes de chamar este.
 */
@Service
public class RegistrarManutencaoService implements RegistrarManutencaoUseCase {

    private final ManutencaoRepositoryPort manutencaoRepositoryPort;
    private final VeiculoRepositoryPort veiculoRepositoryPort;

    public RegistrarManutencaoService(ManutencaoRepositoryPort manutencaoRepositoryPort,
                                       VeiculoRepositoryPort veiculoRepositoryPort) {
        this.manutencaoRepositoryPort = manutencaoRepositoryPort;
        this.veiculoRepositoryPort = veiculoRepositoryPort;
    }

    @Override
    @Transactional
    public Manutencao registrar(RegistrarManutencaoCommand command) {
        Veiculo veiculo = veiculoRepositoryPort.buscarPorId(command.veiculoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Veiculo nao encontrado: id " + command.veiculoId()));

        Manutencao manutencao = new Manutencao(
                null, veiculo.getId(), command.tipo(), command.quilometragemRealizada(),
                command.dataRealizada(), command.intervaloProximaKm(), command.custo());

        Manutencao salva = manutencaoRepositoryPort.salvar(manutencao);

        veiculo.atualizarQuilometragem(command.quilometragemRealizada());
        veiculo.marcarComoDisponivel();
        veiculoRepositoryPort.salvar(veiculo);

        return salva;
    }
}
