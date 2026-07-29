package br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity;

import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("FCEV")
@Getter
@Setter
@NoArgsConstructor
public class VeiculoFCEVJpaEntity extends VeiculoJpaEntity {

    private Double capacidadeTanqueH2Kg;
    private Double pressaoTanqueBar;

    public VeiculoFCEVJpaEntity(Long id, String placa, String modelo, String marca, Integer quilometragemAtual,
                                 StatusVeiculo status, Long empresaId, Double capacidadeTanqueH2Kg, Double pressaoTanqueBar) {
        super(id, placa, modelo, marca, quilometragemAtual, status, empresaId);
        this.capacidadeTanqueH2Kg = capacidadeTanqueH2Kg;
        this.pressaoTanqueBar = pressaoTanqueBar;
    }
}
