package br.com.FutureVehiclesAndFuel.application.port.in;

import br.com.FutureVehiclesAndFuel.application.port.in.command.CadastrarVeiculoCommand;
import br.com.FutureVehiclesAndFuel.domain.model.Veiculo;

public interface CadastrarVeiculoUseCase {
    Veiculo cadastrar(CadastrarVeiculoCommand command);
}
