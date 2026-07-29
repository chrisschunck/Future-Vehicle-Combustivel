package br.com.FutureVehiclesAndFuel.application.service;

import br.com.FutureVehiclesAndFuel.application.port.in.CadastrarVeiculoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.CadastrarVeiculoCommand;
import br.com.FutureVehiclesAndFuel.application.port.out.VeiculoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import br.com.FutureVehiclesAndFuel.domain.model.Veiculo;
import br.com.FutureVehiclesAndFuel.domain.model.VeiculoBEV;
import br.com.FutureVehiclesAndFuel.domain.model.VeiculoFCEV;
import br.com.FutureVehiclesAndFuel.domain.model.VeiculoHibrido;
import org.springframework.stereotype.Service;

@Service
public class CadastrarVeiculoService implements CadastrarVeiculoUseCase {

    private final VeiculoRepositoryPort veiculoRepositoryPort;

    public CadastrarVeiculoService(VeiculoRepositoryPort veiculoRepositoryPort) {
        this.veiculoRepositoryPort = veiculoRepositoryPort;
    }

    @Override
    public Veiculo cadastrar(CadastrarVeiculoCommand command) {
        Veiculo veiculo = switch (command.tipoPropulsao()) {
            case BEV -> new VeiculoBEV(
                    null, command.placa(), command.modelo(), command.marca(),
                    command.quilometragemAtual(), StatusVeiculo.DISPONIVEL, command.empresaId(),
                    command.capacidadeBateriaKwh(), command.autonomiaEstimadaKm(), command.nivelCargaAtualPercentual());
            case FCEV -> new VeiculoFCEV(
                    null, command.placa(), command.modelo(), command.marca(),
                    command.quilometragemAtual(), StatusVeiculo.DISPONIVEL, command.empresaId(),
                    command.capacidadeTanqueH2Kg(), command.pressaoTanqueBar());
            case HIBRIDO -> new VeiculoHibrido(
                    null, command.placa(), command.modelo(), command.marca(),
                    command.quilometragemAtual(), StatusVeiculo.DISPONIVEL, command.empresaId(),
                    command.capacidadeTanqueLitros(), command.capacidadeBateriaKwh());
        };
        return veiculoRepositoryPort.salvar(veiculo);
    }
}
