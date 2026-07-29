package br.com.FutureVehiclesAndFuel.application.service;

import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarAbastecimentosUseCase;
import br.com.FutureVehiclesAndFuel.application.port.out.AbastecimentoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.model.Abastecimento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultarAbastecimentosService implements ConsultarAbastecimentosUseCase {

    private final AbastecimentoRepositoryPort abastecimentoRepositoryPort;

    public ConsultarAbastecimentosService(AbastecimentoRepositoryPort abastecimentoRepositoryPort) {
        this.abastecimentoRepositoryPort = abastecimentoRepositoryPort;
    }

    @Override
    public List<Abastecimento> listarTodos() {
        return abastecimentoRepositoryPort.listarTodos();
    }
}
