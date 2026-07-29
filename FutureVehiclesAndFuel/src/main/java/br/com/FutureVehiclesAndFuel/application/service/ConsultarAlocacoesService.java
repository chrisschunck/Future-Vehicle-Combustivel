package br.com.FutureVehiclesAndFuel.application.service;

import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarAlocacoesUseCase;
import br.com.FutureVehiclesAndFuel.application.port.out.AlocacaoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.model.Alocacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultarAlocacoesService implements ConsultarAlocacoesUseCase {

    private final AlocacaoRepositoryPort alocacaoRepositoryPort;

    public ConsultarAlocacoesService(AlocacaoRepositoryPort alocacaoRepositoryPort) {
        this.alocacaoRepositoryPort = alocacaoRepositoryPort;
    }

    @Override
    public List<Alocacao> listarTodas() {
        return alocacaoRepositoryPort.listarTodos();
    }
}
