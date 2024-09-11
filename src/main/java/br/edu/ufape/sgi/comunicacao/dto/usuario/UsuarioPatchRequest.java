package br.edu.ufape.sgi.comunicacao.dto.usuario;

import br.edu.ufape.sgi.comunicacao.annotations.NumeroValido;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class UsuarioPatchRequest {

    private String nome;

    @Email
    private String email;

    @NumeroValido
    private String telefone;

}
