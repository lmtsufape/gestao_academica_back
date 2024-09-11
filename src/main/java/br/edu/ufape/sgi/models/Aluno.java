package br.edu.ufape.sgi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor

public class Aluno extends Perfil {
    @Column(unique = true)
    private String matricula;
    private String curso;
}
