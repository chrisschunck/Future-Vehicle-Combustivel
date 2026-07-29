package br.com.FutureVehiclesAndFuel.application.port.in;

import br.com.FutureVehiclesAndFuel.domain.model.Motorista;

import java.util.List;

public interface ConsultarMotoristasUseCase {
    List<Motorista> listarTodos();
    Motorista buscarPorId(Long id);
}
