package br.com.FutureVehiclesAndFuel.application.port.in;

import br.com.FutureVehiclesAndFuel.domain.model.Alocacao;

public interface FinalizarAlocacaoUseCase {
    Alocacao finalizar(Long alocacaoId, Integer kmFinal);
}
