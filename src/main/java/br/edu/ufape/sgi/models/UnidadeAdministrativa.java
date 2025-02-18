package br.edu.ufape.sgi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor

public class UnidadeAdministrativa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private TipoUnidadeAdministrativa tipo;
}
