package br.com.FutureVehiclesAndFuel.adapters.in.web.view.viewmodel;

import java.time.LocalDateTime;

public record AlocacaoViewRow(
        Long id, String veiculoPlaca, String motoristaNome, LocalDateTime dataInicio,
        LocalDateTime dataFim, Integer kmInicial, Integer kmFinal, boolean ativa
) {
}
