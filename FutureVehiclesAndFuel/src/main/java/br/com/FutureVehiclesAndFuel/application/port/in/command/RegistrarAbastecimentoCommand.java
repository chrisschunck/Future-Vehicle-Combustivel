package br.com.FutureVehiclesAndFuel.application.port.in.command;

import java.math.BigDecimal;

public record RegistrarAbastecimentoCommand(
        Long veiculoId,
        Double quantidade,
        BigDecimal custo,
        String localEletroposto
) {
}
