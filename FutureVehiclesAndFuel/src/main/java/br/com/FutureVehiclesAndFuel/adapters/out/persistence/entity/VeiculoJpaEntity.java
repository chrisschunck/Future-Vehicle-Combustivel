package br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity;

import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "veiculo")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_propulsao", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
public abstract class VeiculoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String placa;

    private String modelo;
    private String marca;
    private Integer quilometragemAtual;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    private Long empresaId;

    protected VeiculoJpaEntity(Long id, String placa, String modelo, String marca,
                               Integer quilometragemAtual, StatusVeiculo status, Long empresaId) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.quilometragemAtual = quilometragemAtual;
        this.status = status;
        this.empresaId = empresaId;
    }
}
