package br.com.FutureVehiclesAndFuel.adapters.in.web.view.formdata;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AbastecimentoFormData {
    private Long veiculoId;
    private Double quantidade;
    private BigDecimal custo;
    private String localEletroposto;
}
