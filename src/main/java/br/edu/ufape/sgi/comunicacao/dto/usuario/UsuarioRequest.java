package br.edu.ufape.sgi.comunicacao.dto.usuario;



import br.edu.ufape.sgi.comunicacao.annotations.NumeroValido;
import br.edu.ufape.sgi.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @CPF
    private String cpf;

    @Email
    private String email;

    @NumeroValido
    private String telefone;


    public Usuario convertToEntity(UsuarioRequest usuarioRequest, ModelMapper modelMapper) {
        return modelMapper.map(usuarioRequest, Usuario.class);
    }
}
