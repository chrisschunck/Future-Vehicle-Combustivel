package br.com.FutureVehiclesAndFuel.application.port.in;

import br.com.FutureVehiclesAndFuel.application.port.in.command.RegistrarAbastecimentoCommand;
import br.com.FutureVehiclesAndFuel.domain.model.Abastecimento;

public interface RegistrarAbastecimentoUseCase {
    Abastecimento registrar(RegistrarAbastecimentoCommand command);
}
