package br.edu.ufape.sgi.models;

import br.edu.ufape.sgi.models.Enums.TipoPerfil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
public abstract class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public abstract TipoPerfil getTipo();


}
