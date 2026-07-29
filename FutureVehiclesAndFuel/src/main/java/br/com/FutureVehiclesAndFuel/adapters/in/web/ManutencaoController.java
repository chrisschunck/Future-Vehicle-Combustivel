package br.com.FutureVehiclesAndFuel.adapters.in.web;

import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.ManutencaoRequestDTO;
import br.com.FutureVehiclesAndFuel.adapters.in.web.dto.ManutencaoResponseDTO;
import br.com.FutureVehiclesAndFuel.application.port.in.RegistrarManutencaoUseCase;
import br.com.FutureVehiclesAndFuel.application.port.in.command.RegistrarManutencaoCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manutencoes")
public class ManutencaoController {

    private final RegistrarManutencaoUseCase registrarManutencaoUseCase;

    public ManutencaoController(RegistrarManutencaoUseCase registrarManutencaoUseCase) {
        this.registrarManutencaoUseCase = registrarManutencaoUseCase;
    }

    @PostMapping
    public ResponseEntity<ManutencaoResponseDTO> registrar(@Valid @RequestBody ManutencaoRequestDTO dto) {
        RegistrarManutencaoCommand command = new RegistrarManutencaoCommand(
                dto.veiculoId(), dto.tipo(), dto.quilometragemRealizada(), dto.dataRealizada(),
                dto.intervaloProximaKm(), dto.custo());
        var manutencao = registrarManutencaoUseCase.registrar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(ManutencaoResponseDTO.fromDomain(manutencao));
    }
}
