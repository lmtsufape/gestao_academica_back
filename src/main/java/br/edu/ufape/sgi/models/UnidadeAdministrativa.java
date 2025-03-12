package br.edu.ufape.sgi.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnidadeAdministrativa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String codigo;

    @JsonIgnore
    @JsonManagedReference
    @ManyToOne(optional=false)
    @JoinColumn(name = "tipo_unidade_administrativa_id", nullable = false)
    private TipoUnidadeAdministrativa tipoUnidadeAdministrativa;

    //Adicionar relacionamento para representar a nossa arvore 
    @ManyToOne(optional = true)
    @JoinColumn(name = "unidade_pai_id")
    @JsonBackReference
    private UnidadeAdministrativa unidadePai;

    @OneToMany(mappedBy = "unidadePai", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<UnidadeAdministrativa> unidadesFilhas = new ArrayList<>();
}
