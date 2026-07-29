package br.com.FutureVehiclesAndFuel.application.service;

import br.com.FutureVehiclesAndFuel.application.port.in.ConsultarMotoristasUseCase;
import br.com.FutureVehiclesAndFuel.application.port.out.MotoristaRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.exception.EntidadeNaoEncontradaException;
import br.com.FutureVehiclesAndFuel.domain.model.Motorista;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultarMotoristasService implements ConsultarMotoristasUseCase {

    private final MotoristaRepositoryPort motoristaRepositoryPort;

    public ConsultarMotoristasService(MotoristaRepositoryPort motoristaRepositoryPort) {
        this.motoristaRepositoryPort = motoristaRepositoryPort;
    }

    @Override
    public List<Motorista> listarTodos() {
        return motoristaRepositoryPort.listarTodos();
    }

    @Override
    public Motorista buscarPorId(Long id) {
        return motoristaRepositoryPort.buscarPorId(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Motorista nao encontrado: id " + id));
    }
}
