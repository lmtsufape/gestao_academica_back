package br.edu.ufape.sgi.comunicacao.dto.usuario;



import br.edu.ufape.sgi.comunicacao.annotations.NumeroValido;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public abstract class UsuarioRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @CPF
    private String cpf;

    @Email
    private String email;

    @NumeroValido
    private String telefone;
}
