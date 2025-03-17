package br.edu.ufape.sguAuthService.models;

import br.edu.ufape.sguAuthService.models.Enums.TipoPerfil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
public abstract class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public abstract TipoPerfil getTipo();

    // Foto de perfil armazenada em binário
    @Lob
    private byte[] fotoPerfil;  // Usando byte[] para armazenar a imagem em formato binário


}
