package br.com.FutureVehiclesAndFuel.application.port.in.command;

import br.com.FutureVehiclesAndFuel.domain.enums.TipoPropulsao;

public record CadastrarVeiculoCommand(
        String placa,
        String modelo,
        String marca,
        Integer quilometragemAtual,
        Long empresaId,
        TipoPropulsao tipoPropulsao,
        // BEV
        Double capacidadeBateriaKwh,
        Integer autonomiaEstimadaKm,
        Double nivelCargaAtualPercentual,
        // FCEV
        Double capacidadeTanqueH2Kg,
        Double pressaoTanqueBar,
        // Hibrido
        Double capacidadeTanqueLitros
) {
}
