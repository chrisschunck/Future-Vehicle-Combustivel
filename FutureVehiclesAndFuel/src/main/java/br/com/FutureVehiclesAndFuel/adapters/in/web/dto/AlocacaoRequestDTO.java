package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import jakarta.validation.constraints.NotNull;

public record AlocacaoRequestDTO(
        @NotNull Long veiculoId,
        @NotNull Long motoristaId,
        Integer kmInicial
) {
}
