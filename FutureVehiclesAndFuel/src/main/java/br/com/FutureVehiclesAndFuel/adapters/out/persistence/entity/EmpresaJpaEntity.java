package br.com.FutureVehiclesAndFuel.adapters.out.persistence.entity;

import br.com.FutureVehiclesAndFuel.domain.enums.TipoOperacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razaoSocial;

    @Column(unique = true)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    private TipoOperacao tipoOperacao;
}
