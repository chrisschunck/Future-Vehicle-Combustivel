package br.com.FutureVehiclesAndFuel.domain.exception;

public class AlocacaoAtivaExistenteException extends RuntimeException {
    public AlocacaoAtivaExistenteException(String message) {
        super(message);
    }
}
