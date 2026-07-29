package br.com.FutureVehiclesAndFuel.application.port.out;

import br.com.FutureVehiclesAndFuel.domain.model.Manutencao;

import java.util.List;

public interface ManutencaoRepositoryPort {
    Manutencao salvar(Manutencao manutencao);
    List<Manutencao> listarTodos();
}
