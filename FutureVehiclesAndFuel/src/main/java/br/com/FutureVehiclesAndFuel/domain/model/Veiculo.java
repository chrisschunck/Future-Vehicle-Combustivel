package br.com.FutureVehiclesAndFuel.domain.model;

import br.com.FutureVehiclesAndFuel.domain.enums.StatusVeiculo;
import br.com.FutureVehiclesAndFuel.domain.enums.TipoPropulsao;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe base de dominio. POJO puro, sem anotacoes de JPA/Spring.
 * As regras de negocio do veiculo vivem aqui, nao na entity de persistencia.
 */
@Getter
@Setter
public abstract class Veiculo {

    private Long id;
    private String placa;
    private String modelo;
    private String marca;
    private Integer quilometragemAtual;
    private StatusVeiculo status;
    private Long empresaId;

    protected Veiculo(Long id, String placa, String modelo, String marca,
                       Integer quilometragemAtual, StatusVeiculo status, Long empresaId) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.quilometragemAtual = quilometragemAtual;
        this.status = status == null ? StatusVeiculo.DISPONIVEL : status;
        this.empresaId = empresaId;
    }

    public abstract TipoPropulsao getTipoPropulsao();

    public boolean estaDisponivel() {
        return this.status == StatusVeiculo.DISPONIVEL;
    }

    public void marcarComoEmUso() {
        if (!estaDisponivel()) {
            throw new br.com.FutureVehiclesAndFuel.domain.exception.VeiculoIndisponivelException(
                    "Veiculo de placa " + placa + " nao esta disponivel (status atual: " + status + ")");
        }
        this.status = StatusVeiculo.EM_USO;
    }

    public void marcarComoDisponivel() {
        this.status = StatusVeiculo.DISPONIVEL;
    }

    public void marcarComoEmManutencao() {
        this.status = StatusVeiculo.MANUTENCAO;
    }

    public void atualizarQuilometragem(Integer novaQuilometragem) {
        if (novaQuilometragem != null && this.quilometragemAtual != null
                && novaQuilometragem < this.quilometragemAtual) {
            throw new IllegalArgumentException("A nova quilometragem nao pode ser menor que a atual");
        }
        this.quilometragemAtual = novaQuilometragem;
    }
}
