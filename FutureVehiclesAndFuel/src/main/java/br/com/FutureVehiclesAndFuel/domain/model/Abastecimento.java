package br.com.FutureVehiclesAndFuel.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Abastecimento {
    private Long id;
    private Long veiculoId;
    private LocalDateTime dataHora;
    private Double quantidade;
    private BigDecimal custo;
    private String localEletroposto;
}
