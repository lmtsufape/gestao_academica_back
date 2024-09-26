package br.edu.ufape.sgi.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Professor extends Funcionario {
    private String curso;

    @Override
    public String getTipo() {
        return "Professor";
    }
}
