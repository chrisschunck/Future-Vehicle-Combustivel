package br.com.FutureVehiclesAndFuel.domain.model;

import br.com.FutureVehiclesAndFuel.domain.enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    private Long id;
    private String razaoSocial;
    private String cnpj;
    private TipoOperacao tipoOperacao;
}
