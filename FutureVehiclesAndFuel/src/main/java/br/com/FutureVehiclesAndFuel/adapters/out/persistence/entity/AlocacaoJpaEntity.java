package br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "alocacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlocacaoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long veiculoId;
    private Long motoristaId;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer kmInicial;
    private Integer kmFinal;
}
