package br.edu.ufape.sguAuthService.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_unidade_administrativa_id", nullable = false)
    @JsonManagedReference
    private TipoUnidadeAdministrativa tipoUnidadeAdministrativa;

    @ManyToOne
    @JoinColumn(name = "unidade_pai_id")
    @JsonBackReference
    private UnidadeAdministrativa unidadePai;

    @OneToMany(mappedBy = "unidadePai", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    private List<UnidadeAdministrativa> unidadesFilhas = new ArrayList<>();
}