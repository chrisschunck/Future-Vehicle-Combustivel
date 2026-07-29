package br.com.FutureVehiclesAndFuel.application.port.in.command;

public record AlocarVeiculoCommand(
        Long veiculoId,
        Long motoristaId,
        Integer kmInicial
) {
}
