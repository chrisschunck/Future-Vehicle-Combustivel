package br.com.FutureVehiclesAndFuel.adapters.out.persistence.adapter;

import br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity.*;
import br.com.FutureVehiclesAndFuel.adapters.out.persistence.repository.VeiculoJpaRepository;
import br.com.FutureVehiclesAndFuel.application.port.out.VeiculoRepositoryPort;
import br.com.FutureVehiclesAndFuel.domain.model.Veiculo;
import br.com.FutureVehiclesAndFuel.domain.model.VeiculoBEV;
import br.com.FutureVehiclesAndFuel.domain.model.VeiculoFCEV;
import br.com.FutureVehiclesAndFuel.domain.model.VeiculoHibrido;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Adapter que implementa o port de saida (VeiculoRepositoryPort) usando JPA.
 * E aqui que acontece a conversao Model (dominio) <-> Entity (persistencia).
 */
@Component
public class VeiculoRepositoryAdapter implements VeiculoRepositoryPort {

    private final VeiculoJpaRepository jpaRepository;

    public VeiculoRepositoryAdapter(VeiculoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Veiculo salvar(Veiculo veiculo) {
        VeiculoJpaEntity entity = toEntity(veiculo);
        VeiculoJpaEntity salvo = jpaRepository.save(entity);
        return toDomain(salvo);
    }

    @Override
    public Optional<Veiculo> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Veiculo> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private VeiculoJpaEntity toEntity(Veiculo veiculo) {
        if (veiculo instanceof VeiculoBEV bev) {
            return new VeiculoBEVJpaEntity(
                    bev.getId(), bev.getPlaca(), bev.getModelo(), bev.getMarca(), bev.getQuilometragemAtual(),
                    bev.getStatus(), bev.getEmpresaId(), bev.getCapacidadeBateriaKwh(),
                    bev.getAutonomiaEstimadaKm(), bev.getNivelCargaAtualPercentual());
        }
        if (veiculo instanceof VeiculoFCEV fcev) {
            return new VeiculoFCEVJpaEntity(
                    fcev.getId(), fcev.getPlaca(), fcev.getModelo(), fcev.getMarca(), fcev.getQuilometragemAtual(),
                    fcev.getStatus(), fcev.getEmpresaId(), fcev.getCapacidadeTanqueH2Kg(), fcev.getPressaoTanqueBar());
        }
        if (veiculo instanceof VeiculoHibrido hibrido) {
            return new VeiculoHibridoJpaEntity(
                    hibrido.getId(), hibrido.getPlaca(), hibrido.getModelo(), hibrido.getMarca(),
                    hibrido.getQuilometragemAtual(), hibrido.getStatus(), hibrido.getEmpresaId(),
                    hibrido.getCapacidadeTanqueLitros(), hibrido.getCapacidadeBateriaKwh());
        }
        throw new IllegalArgumentException("Tipo de veiculo desconhecido: " + veiculo.getClass());
    }

    private Veiculo toDomain(VeiculoJpaEntity entity) {
        if (entity instanceof VeiculoBEVJpaEntity bev) {
            return new VeiculoBEV(
                    bev.getId(), bev.getPlaca(), bev.getModelo(), bev.getMarca(), bev.getQuilometragemAtual(),
                    bev.getStatus(), bev.getEmpresaId(), bev.getCapacidadeBateriaKwh(),
                    bev.getAutonomiaEstimadaKm(), bev.getNivelCargaAtualPercentual());
        }
        if (entity instanceof VeiculoFCEVJpaEntity fcev) {
            return new VeiculoFCEV(
                    fcev.getId(), fcev.getPlaca(), fcev.getModelo(), fcev.getMarca(), fcev.getQuilometragemAtual(),
                    fcev.getStatus(), fcev.getEmpresaId(), fcev.getCapacidadeTanqueH2Kg(), fcev.getPressaoTanqueBar());
        }
        if (entity instanceof VeiculoHibridoJpaEntity hibrido) {
            return new VeiculoHibrido(
                    hibrido.getId(), hibrido.getPlaca(), hibrido.getModelo(), hibrido.getMarca(),
                    hibrido.getQuilometragemAtual(), hibrido.getStatus(), hibrido.getEmpresaId(),
                    hibrido.getCapacidadeTanqueLitros(), hibrido.getCapacidadeBateriaKwh());
        }
        throw new IllegalArgumentException("Tipo de veiculo desconhecido: " + entity.getClass());
    }
}
