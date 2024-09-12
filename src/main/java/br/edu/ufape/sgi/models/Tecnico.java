package br.edu.ufape.sgi.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Tecnico extends Funcionario {
    @Override
    public String getTipo() {
        return "Tecnico";
    }

}
