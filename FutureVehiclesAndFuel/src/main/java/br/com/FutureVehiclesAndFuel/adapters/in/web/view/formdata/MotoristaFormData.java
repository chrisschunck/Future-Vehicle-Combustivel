package br.com.FutureVehiclesAndFuel.adapters.in.web.view.formdata;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MotoristaFormData {
    private String nome;
    private String cnh;
    private LocalDate validadeCnh;
    private Long empresaId = 1L;
}
