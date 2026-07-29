package br.com.FutureVehiclesAndFuel.adapters.in.web.view.formdata;

import br.com.FutureVehiclesAndFuel.domain.enums.TipoPropulsao;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe de apoio somente para o binding do formulario Thymeleaf (th:object).
 * Nao e a entidade nem o command do caso de uso - e so a "casca" que recebe
 * os dados brutos do <form> antes de serem convertidos em CadastrarVeiculoCommand.
 */
@Getter
@Setter
public class VeiculoFormData {
    private String placa;
    private String modelo;
    private String marca;
    private Integer quilometragemAtual = 0;
    private Long empresaId = 1L;
    private TipoPropulsao tipoPropulsao;

    private Double capacidadeBateriaKwh;
    private Integer autonomiaEstimadaKm;
    private Double nivelCargaAtualPercentual;

    private Double capacidadeTanqueH2Kg;
    private Double pressaoTanqueBar;

    private Double capacidadeTanqueLitros;
}
