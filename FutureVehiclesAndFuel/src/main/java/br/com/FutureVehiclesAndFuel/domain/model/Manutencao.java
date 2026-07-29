package br.com.FutureVehiclesAndFuel.domain.model;

import br.com.FutureVehiclesAndFuel.domain.enums.TipoManutencao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manutencao {
    private Long id;
    private Long veiculoId;
    private TipoManutencao tipo;
    private Integer quilometragemRealizada;
    private LocalDate dataRealizada;
    private Integer intervaloProximaKm;
    private BigDecimal custo;
}
