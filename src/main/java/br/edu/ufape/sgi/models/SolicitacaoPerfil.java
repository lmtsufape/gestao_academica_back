package br.edu.ufape.sgi.models;


import br.edu.ufape.sgi.models.Enums.StatusSolicitacao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}
