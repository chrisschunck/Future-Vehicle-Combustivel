package br.com.FutureVehiclesAndFuel.application.port.in;

import br.com.FutureVehiclesAndFuel.domain.model.Manutencao;

import java.util.List;

public interface ConsultarManutencoesUseCase {
    List<Manutencao> listarTodas();
}
