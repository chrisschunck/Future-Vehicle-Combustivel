package br.com.FutureVehiclesAndFuel.adapters.in.web.view.formdata;

import br.com.FutureVehiclesAndFuel.domain.enums.TipoManutencao;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ManutencaoFormData {
    private Long veiculoId;
    private TipoManutencao tipo;
    private Integer quilometragemRealizada;
    private LocalDate dataRealizada = LocalDate.now();
    private Integer intervaloProximaKm;
    private BigDecimal custo;
}
