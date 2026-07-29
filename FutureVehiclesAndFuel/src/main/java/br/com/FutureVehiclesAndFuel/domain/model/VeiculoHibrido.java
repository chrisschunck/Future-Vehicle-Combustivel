package br.com.FutureVehiclesAndFuel.domain.model;

import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import br.com.FutureVehiclesAndFuel.domain.enums.TipoPropulsao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoHibrido extends Veiculo {

    private Double capacidadeTanqueLitros;
    private Double capacidadeBateriaKwh;

    public VeiculoHibrido(Long id, String placa, String modelo, String marca, Integer quilometragemAtual,
                           StatusVeiculo status, Long empresaId, Double capacidadeTanqueLitros, Double capacidadeBateriaKwh) {
        super(id, placa, modelo, marca, quilometragemAtual, status, empresaId);
        this.capacidadeTanqueLitros = capacidadeTanqueLitros;
        this.capacidadeBateriaKwh = capacidadeBateriaKwh;
    }

    @Override
    public TipoPropulsao getTipoPropulsao() {
        return TipoPropulsao.HIBRIDO;
    }
}
