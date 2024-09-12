package br.edu.ufape.sgi.models;

import jakarta.persistence.Entity;

@Entity
public class Visitante extends Perfil {

    @Override
    public String getTipo() {
        return "Visitante";
    }
}
