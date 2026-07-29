package br.com.FutureVehiclesAndFuel.application.port.in.command;

import br.com.FutureVehiclesAndFuel.domain.enums.TipoManutencao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RegistrarManutencaoCommand(
        Long veiculoId,
        TipoManutencao tipo,
        Integer quilometragemRealizada,
        LocalDate dataRealizada,
        Integer intervaloProximaKm,
        BigDecimal custo
) {
}
