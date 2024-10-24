package br.edu.ufape.sgi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        Usuario usuario = (Usuario) o;
        return getId() != null && Objects.equals(getId(), usuario.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }

    public Aluno getAluno() {
        return perfis.stream().filter(perfil -> perfil instanceof Aluno).map(perfil -> (Aluno) perfil).findFirst().orElseThrow();
    }

    public Professor getProfessor() {
        return perfis.stream().filter(perfil -> perfil instanceof Professor).map(perfil -> (Professor) perfil).findFirst().orElseThrow();
    }

    public Tecnico getTecnico() {
        return perfis.stream().filter(perfil -> perfil instanceof Tecnico).map(perfil -> (Tecnico) perfil).findFirst().orElseThrow();
    }
}
