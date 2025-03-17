package br.edu.ufape.sguAuthService.models;

import br.edu.ufape.sguAuthService.models.Enums.TipoPerfil;
import jakarta.persistence.Entity;

@Entity
public class Visitante extends Perfil {

    @Override
    public TipoPerfil getTipo() {
        return TipoPerfil.VISITANTE;
    }
}
