package br.edu.ufape.sguAuthService.models;

import br.edu.ufape.sguAuthService.models.Enums.TipoPerfil;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Professor extends Funcionario {

    @ManyToMany
    private Set<Curso> cursos = new HashSet<>();

    @Override
    public TipoPerfil getTipo() {
        return TipoPerfil.PROFESSOR;
    }


}
