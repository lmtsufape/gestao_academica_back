package br.edu.ufape.sgi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kcId;
    private String nome;
    private String nomeSocial;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String telefone;
    private Boolean ativo = true;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Set<Perfil> perfis = new  HashSet<>();

    public void adicionarPerfil(Perfil perfil) {
        perfis.add(perfil);
    }
}
