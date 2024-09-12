package br.edu.ufape.sgi.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public abstract class Funcionario extends Perfil {
    private String siape;
}
