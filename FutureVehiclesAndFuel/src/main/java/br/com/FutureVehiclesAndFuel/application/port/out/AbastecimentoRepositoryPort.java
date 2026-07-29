package br.com.FutureVehiclesAndFuel.application.port.out;

import br.com.FutureVehiclesAndFuel.domain.model.Abastecimento;

import java.util.List;

public interface AbastecimentoRepositoryPort {
    Abastecimento salvar(Abastecimento abastecimento);
    List<Abastecimento> listarTodos();
}
