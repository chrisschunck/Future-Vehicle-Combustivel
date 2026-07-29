package br.com.FutureVehiclesAndFuel.application.port.in;

import br.com.FutureVehiclesAndFuel.application.port.in.command.AlocarVeiculoCommand;
import br.com.FutureVehiclesAndFuel.domain.model.Alocacao;

public interface AlocarVeiculoUseCase {
    Alocacao alocar(AlocarVeiculoCommand command);
}
