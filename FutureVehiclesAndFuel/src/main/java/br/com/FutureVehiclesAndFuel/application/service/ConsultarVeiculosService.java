package br.com.FutureVehiclesAndFuel.application.service;

import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarVeiculosUseCase;
import br.com.FutureVehiclesAndFuel.application.port.out.VeiculoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.exception.EntidadeNaoEncontradaException;
import br.com.FutureVehiclesAndFuel.domain.model.Veiculo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultarVeiculosService implements ConsultarVeiculosUseCase {

    private final VeiculoRepositoryPort veiculoRepositoryPort;

    public ConsultarVeiculosService(VeiculoRepositoryPort veiculoRepositoryPort) {
        this.veiculoRepositoryPort = veiculoRepositoryPort;
    }

    @Override
    public List<Veiculo> listarTodos() {
        return veiculoRepositoryPort.listarTodos();
    }

    @Override
    public Veiculo buscarPorId(Long id) {
        return veiculoRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Veiculo nao encontrado: id " + id));
    }
}
