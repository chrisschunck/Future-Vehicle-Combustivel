package br.com.FutureVehiclesAndFuel.application.service;

import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarManutencoesUseCase;
import br.com.FutureVehiclesAndFuel.application.port.out.ManutencaoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.model.Manutencao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultarManutencoesService implements ConsultarManutencoesUseCase {

    private final ManutencaoRepositoryPort manutencaoRepositoryPort;

    public ConsultarManutencoesService(ManutencaoRepositoryPort manutencaoRepositoryPort) {
        this.manutencaoRepositoryPort = manutencaoRepositoryPort;
    }

    @Override
    public List<Manutencao> listarTodas() {
        return manutencaoRepositoryPort.listarTodos();
    }
}
