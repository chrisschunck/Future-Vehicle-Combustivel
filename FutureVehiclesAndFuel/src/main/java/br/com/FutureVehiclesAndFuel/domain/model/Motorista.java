package br.com.FutureVehiclesAndFuel.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Motorista {
    private Long id;
    private String nome;
    private String cnh;
    private LocalDate validadeCnh;
    private Long empresaId;

    public boolean cnhValida() {
        return validadeCnh != null && !validadeCnh.isBefore(LocalDate.now());
    }
}
