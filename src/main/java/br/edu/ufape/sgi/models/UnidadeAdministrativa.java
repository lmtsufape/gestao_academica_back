package br.edu.ufape.sgi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.OneToMany;

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
}
