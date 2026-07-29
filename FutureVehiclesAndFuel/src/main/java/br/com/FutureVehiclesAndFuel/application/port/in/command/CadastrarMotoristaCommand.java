package br.com.FutureVehiclesAndFuel.application.port.in.command;

import java.time.LocalDate;

public record CadastrarMotoristaCommand(
        String nome,
        String cnh,
        LocalDate validadeCnh,
        Long empresaId
) {
}
