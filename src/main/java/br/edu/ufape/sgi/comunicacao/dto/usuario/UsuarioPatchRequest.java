package br.edu.ufape.sgi.comunicacao.dto.usuario;

import br.edu.ufape.sgi.comunicacao.annotations.NumeroValido;
import br.edu.ufape.sgi.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPatchRequest {

    private String nome;

    @NumeroValido
    private String telefone;

    public Usuario convertToEntity(UsuarioPatchRequest usuarioRequest, ModelMapper modelMapper) {
        return modelMapper.map(usuarioRequest, Usuario.class);
    }
}
