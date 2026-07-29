package br.com.FutureVehiclesAndFuel.application.service;

import br.com.FutureVehiclesAndFuel.application.port.in.CadastrarMotoristaUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.CadastrarMotoristaCommand;
import br.com.FutureVehiclesAndFuel.application.port.out.MotoristaRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.model.Motorista;
import org.springframework.stereotype.Service;

@Service
public class CadastrarMotoristaService implements CadastrarMotoristaUseCase {

    private final MotoristaRepositoryPort motoristaRepositoryPort;

    public CadastrarMotoristaService(MotoristaRepositoryPort motoristaRepositoryPort) {
        this.motoristaRepositoryPort = motoristaRepositoryPort;
    }

    @Override
    public Motorista cadastrar(CadastrarMotoristaCommand command) {
        Motorista motorista = new Motorista(
                null, command.nome(), command.cnh(), command.validadeCnh(), command.empresaId());
        return motoristaRepositoryPort.salvar(motorista);
    }
}
