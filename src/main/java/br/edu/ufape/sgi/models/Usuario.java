package br.edu.ufape.sgi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

@Getter @Setter @NoArgsConstructor @AllArgsConstructor

public abstract class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String kcId;
    private String nome;
    private String nomeSocial;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
}
