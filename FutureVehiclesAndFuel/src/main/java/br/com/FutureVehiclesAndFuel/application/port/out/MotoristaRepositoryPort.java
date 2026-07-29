package br.com.FutureVehiclesAndFuel.application.port.out;

import br.com.FutureVehiclesAndFuel.domain.model.Motorista;

import java.util.List;
import java.util.Optional;

public interface MotoristaRepositoryPort {
    Motorista salvar(Motorista motorista);
    Optional<Motorista> buscarPorId(Long id);
    List<Motorista> listarTodos();
}
