package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import br.com.FutureVehiclesAndFuel.domain.model.Abastecimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AbastecimentoResponseDTO(
        Long id, Long veiculoId, LocalDateTime dataHora, Double quantidade,
        BigDecimal custo, String localEletroposto
) {
    public static AbastecimentoResponseDTO fromDomain(Abastecimento abastecimento) {
        return new AbastecimentoResponseDTO(abastecimento.getId(), abastecimento.getVeiculoId(),
                abastecimento.getDataHora(), abastecimento.getQuantidade(),
                abastecimento.getCusto(), abastecimento.getLocalEletroposto());
    }
}
