package br.edu.ufape.sgi.models;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class TipoUnidadeAdministrativa {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;


    @OneToMany(mappedBy = "tipoUnidadeAdministrativa")
    @JsonManagedReference
    private List<UnidadeAdministrativa> unidadeAdministrativa= new ArrayList<>();

    public String getNome() {
        return nome;
    }

    
}
