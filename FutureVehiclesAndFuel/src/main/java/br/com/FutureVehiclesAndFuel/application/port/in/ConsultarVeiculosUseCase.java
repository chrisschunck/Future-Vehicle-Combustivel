package br.com.FutureVehiclesAndFuel.application.port.in;

import br.com.FutureVehiclesAndFuel.domain.model.Veiculo;

import java.util.List;

public interface ConsultarVeiculosUseCase {
    List<Veiculo> listarTodos();
    Veiculo buscarPorId(Long id);
}
