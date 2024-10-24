package br.edu.ufape.sgi.comunicacao.dto.usuario;



import br.edu.ufape.sgi.comunicacao.annotations.NumeroValido;
import br.edu.ufape.sgi.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.modelmapper.ModelMapper;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UsuarioRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    private String nomeSocial;

    @CPF
    private String cpf;

    @Email
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

    @NumeroValido
    private String telefone;


    public Usuario convertToEntity(UsuarioRequest usuarioRequest, ModelMapper modelMapper) {
        return modelMapper.map(usuarioRequest, Usuario.class);
    }
}
