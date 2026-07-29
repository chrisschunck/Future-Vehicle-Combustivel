package br.com.FutureVehiclesAndFuel.application.port.in;

import br.com.FutureVehiclesAndFuel.application.port.in.command.RegistrarManutencaoCommand;
import br.com.FutureVehiclesAndFuel.domain.model.Manutencao;

public interface RegistrarManutencaoUseCase {
    Manutencao registrar(RegistrarManutencaoCommand command);
}
