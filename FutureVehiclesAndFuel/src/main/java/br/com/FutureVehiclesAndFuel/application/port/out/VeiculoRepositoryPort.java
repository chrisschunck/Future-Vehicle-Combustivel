package br.com.FutureVehiclesAndFuel.application.port.out;

import br.com.FutureVehiclesAndFuel.domain.model.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepositoryPort {
    Veiculo salvar(Veiculo veiculo);
    Optional<Veiculo> buscarPorId(Long id);
    List<Veiculo> listarTodos();
}
