package br.edu.ufape.sgi.models;

import br.edu.ufape.sgi.models.Enums.TipoPerfil;
import jakarta.persistence.Entity;

@Entity
public class Visitante extends Perfil {

    @Override
    public TipoPerfil getTipo() {
        return TipoPerfil.VISITANTE;
    }
}
