package br.edu.ufape.sgi.comunicacao.dto.usuario;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public abstract class UsuarioResponse {
    Long id;
    String nome;
    String cpf;
    String email;
    String telefone;
}
