package br.com.FutureVehiclesAndFuel.application.port.in;

import br.com.FutureVehiclesAndFuel.domain.model.Abastecimento;

import java.util.List;

public interface ConsultarAbastecimentosUseCase {
    List<Abastecimento> listarTodos();
}
