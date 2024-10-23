package br.edu.ufape.sgi.models;

import br.edu.ufape.sgi.models.Enums.TipoPerfil;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Tecnico extends Funcionario {
    @Override
    public TipoPerfil getTipo() {
        return TipoPerfil.TECNICO;
    }

}
