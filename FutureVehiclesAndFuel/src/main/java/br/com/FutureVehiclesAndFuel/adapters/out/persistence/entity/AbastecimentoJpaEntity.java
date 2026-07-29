package br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "abastecimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbastecimentoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long veiculoId;
    private LocalDateTime dataHora;
    private Double quantidade;
    private BigDecimal custo;
    private String localEletroposto;
}
