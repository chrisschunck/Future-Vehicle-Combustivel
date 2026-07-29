package br.com.FutureVehiclesAndFuel.adapters.in.web.dto;

import br.com.FutureVehiclesAndFuel.domain.model.Motorista;

import java.time.LocalDate;

public record MotoristaResponseDTO(
        Long id, String nome, String cnh, LocalDate validadeCnh, Long empresaId, boolean cnhValida
) {
    public static MotoristaResponseDTO fromDomain(Motorista motorista) {
        return new MotoristaResponseDTO(motorista.getId(), motorista.getNome(), motorista.getCnh(),
                motorista.getValidadeCnh(), motorista.getEmpresaId(), motorista.cnhValida());
    }
}
