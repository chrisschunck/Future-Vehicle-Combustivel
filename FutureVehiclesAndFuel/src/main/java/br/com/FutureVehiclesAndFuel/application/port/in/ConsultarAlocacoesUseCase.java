package br.com.FutureVehiclesAndFuel.application.port.in;

import br.com.FutureVehiclesAndFuel.domain.model.Alocacao;

import java.util.List;

public interface ConsultarAlocacoesUseCase {
    List<Alocacao> listarTodas();
}
