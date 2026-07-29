package br.com.FutureVehiclesAndFuel.application.port.in;

import br.com.FutureVehiclesAndFuel.application.port.in.command.CadastrarMotoristaCommand;
import br.com.FutureVehiclesAndFuel.domain.model.Motorista;

public interface CadastrarMotoristaUseCase {
    Motorista cadastrar(CadastrarMotoristaCommand command);
}
