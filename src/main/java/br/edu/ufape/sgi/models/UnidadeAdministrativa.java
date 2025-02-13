package br.edu.ufape.sgi.models;

import br.edu.ufape.sgi.models.Enums.TipoUnidadeAdministrativa;
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

    @Enumerated(EnumType.STRING)
    private TipoUnidadeAdministrativa tipo;
}
