package br.edu.ufape.sgi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor

public class Aluno extends Perfil {
    @Column(unique = true)
    private String matricula;

    @ManyToOne
    private Curso curso;

    @Override
    public String getTipo() {
        return "Aluno";
    }
}
