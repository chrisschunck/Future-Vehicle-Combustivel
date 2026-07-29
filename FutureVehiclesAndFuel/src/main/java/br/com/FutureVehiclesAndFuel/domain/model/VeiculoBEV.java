package br.com.FutureVehiclesAndFuel.domain.model;

import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import br.com.FutureVehiclesAndFuel.domain.enums.TipoPropulsao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoBEV extends Veiculo {

    private Double capacidadeBateriaKwh;
    private Integer autonomiaEstimadaKm;
    private Double nivelCargaAtualPercentual;

    public VeiculoBEV(Long id, String placa, String modelo, String marca, Integer quilometragemAtual,
                       StatusVeiculo status, Long empresaId, Double capacidadeBateriaKwh,
                       Integer autonomiaEstimadaKm, Double nivelCargaAtualPercentual) {
        super(id, placa, modelo, marca, quilometragemAtual, status, empresaId);
        this.capacidadeBateriaKwh = capacidadeBateriaKwh;
        this.autonomiaEstimadaKm = autonomiaEstimadaKm;
        this.nivelCargaAtualPercentual = nivelCargaAtualPercentual;
    }

    @Override
    public TipoPropulsao getTipoPropulsao() {
        return TipoPropulsao.BEV;
    }
}
