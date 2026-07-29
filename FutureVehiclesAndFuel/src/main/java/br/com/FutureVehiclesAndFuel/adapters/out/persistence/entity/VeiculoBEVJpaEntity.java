package br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity;

import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("BEV")
@Getter
@Setter
@NoArgsConstructor
public class VeiculoBEVJpaEntity extends VeiculoJpaEntity {

    private Double capacidadeBateriaKwh;
    private Integer autonomiaEstimadaKm;
    private Double nivelCargaAtualPercentual;

    public VeiculoBEVJpaEntity(Long id, String placa, String modelo, String marca, Integer quilometragemAtual,
                                StatusVeiculo status, Long empresaId, Double capacidadeBateriaKwh,
                                Integer autonomiaEstimadaKm, Double nivelCargaAtualPercentual) {
        super(id, placa, modelo, marca, quilometragemAtual, status, empresaId);
        this.capacidadeBateriaKwh = capacidadeBateriaKwh;
        this.autonomiaEstimadaKm = autonomiaEstimadaKm;
        this.nivelCargaAtualPercentual = nivelCargaAtualPercentual;
    }
}
