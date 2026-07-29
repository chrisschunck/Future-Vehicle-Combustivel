package br.com.FutureVehiclesAndFuel.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alocacao {
    private Long id;
    private Long veiculoId;
    private Long motoristaId;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer kmInicial;
    private Integer kmFinal;

    public boolean estaAtiva() {
        return dataFim == null;
    }

    public void finalizar(Integer kmFinal) {
        if (!estaAtiva()) {
            throw new IllegalStateException("Alocacao ja foi finalizada");
        }
        if (kmInicial != null && kmFinal != null && kmFinal < kmInicial) {
            throw new IllegalArgumentException("Km final nao pode ser menor que o km inicial");
        }
        this.dataFim = LocalDateTime.now();
        this.kmFinal = kmFinal;
    }
}
