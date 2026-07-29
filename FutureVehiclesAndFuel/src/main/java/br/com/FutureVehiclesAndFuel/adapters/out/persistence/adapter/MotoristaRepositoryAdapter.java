package br.com.FutureVehiclesAndFuel.adapters.out.persistence.adapter;

import br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity.MotoristaJpaEntity;
import br.com.FutureVehiclesAndFuel.adapters.out.persistence.repository.MotoristaJpaRepository;
import br.com.FutureVehiclesAndFuel.application.port.out.MotoristaRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.model.Motorista;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MotoristaRepositoryAdapter implements MotoristaRepositoryPort {

    private final MotoristaJpaRepository jpaRepository;

    public MotoristaRepositoryAdapter(MotoristaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Motorista salvar(Motorista motorista) {
        MotoristaJpaEntity entity = new MotoristaJpaEntity(
                motorista.getId(), motorista.getNome(), motorista.getCnh(),
                motorista.getValidadeCnh(), motorista.getEmpresaId());
        MotoristaJpaEntity salvo = jpaRepository.save(entity);
        return toDomain(salvo);
    }

    @Override
    public Optional<Motorista> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Motorista> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private Motorista toDomain(MotoristaJpaEntity entity) {
        return new Motorista(entity.getId(), entity.getNome(), entity.getCnh(),
                entity.getValidadeCnh(), entity.getEmpresaId());
    }
}
