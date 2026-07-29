package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AbastecimentoRequestDTO(
        @NotNull Long veiculoId,
        @NotNull Double quantidade,
        BigDecimal custo,
        String localEletroposto
) {
}
