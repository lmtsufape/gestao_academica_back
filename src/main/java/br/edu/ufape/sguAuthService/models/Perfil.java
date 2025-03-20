package br.edu.ufape.sguAuthService.models;

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


    // Foto de perfil armazenada em binário
    @Lob
    private byte[] fotoPerfil;  // Usando byte[] para armazenar a imagem em formato binário


}
