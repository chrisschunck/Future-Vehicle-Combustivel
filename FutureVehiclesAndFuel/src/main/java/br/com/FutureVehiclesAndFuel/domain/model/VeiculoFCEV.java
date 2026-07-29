package br.com.FutureVehiclesAndFuel.domain.model;

import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import br.com.FutureVehiclesAndFuel.domain.enums.TipoPropulsao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoFCEV extends Veiculo {

    private Double capacidadeTanqueH2Kg;
    private Double pressaoTanqueBar;

    public VeiculoFCEV(Long id, String placa, String modelo, String marca, Integer quilometragemAtual,
                        StatusVeiculo status, Long empresaId, Double capacidadeTanqueH2Kg, Double pressaoTanqueBar) {
        super(id, placa, modelo, marca, quilometragemAtual, status, empresaId);
        this.capacidadeTanqueH2Kg = capacidadeTanqueH2Kg;
        this.pressaoTanqueBar = pressaoTanqueBar;
    }

    @Override
    public TipoPropulsao getTipoPropulsao() {
        return TipoPropulsao.FCEV;
    }
}
