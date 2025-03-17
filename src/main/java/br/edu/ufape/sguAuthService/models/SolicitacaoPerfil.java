package br.edu.ufape.sguAuthService.models;


import br.edu.ufape.sguAuthService.models.Enums.StatusSolicitacao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class SolicitacaoPerfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataSolicitacao;

    private StatusSolicitacao status;

    private LocalDateTime dataAvaliacao;

    private String parecer;

    @OneToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos = new ArrayList<>();

    @ManyToOne
    private Usuario solicitante;

    @ManyToOne
    private Usuario responsavel;

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
        SolicitacaoPerfil solicitacaoPerfil = (SolicitacaoPerfil) o;
        return getId() != null && Objects.equals(getId(), solicitacaoPerfil.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
