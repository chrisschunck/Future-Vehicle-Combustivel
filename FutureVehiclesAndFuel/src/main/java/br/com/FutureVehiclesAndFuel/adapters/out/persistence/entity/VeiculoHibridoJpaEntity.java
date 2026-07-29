package br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity;

import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("HIBRIDO")
@Getter
@Setter
@NoArgsConstructor
public class VeiculoHibridoJpaEntity extends VeiculoJpaEntity {

    private Double capacidadeTanqueLitros;
    private Double capacidadeBateriaKwh;

    public VeiculoHibridoJpaEntity(Long id, String placa, String modelo, String marca, Integer quilometragemAtual,
                                    StatusVeiculo status, Long empresaId, Double capacidadeTanqueLitros, Double capacidadeBateriaKwh) {
        super(id, placa, modelo, marca, quilometragemAtual, status, empresaId);
        this.capacidadeTanqueLitros = capacidadeTanqueLitros;
        this.capacidadeBateriaKwh = capacidadeBateriaKwh;
    }
}
