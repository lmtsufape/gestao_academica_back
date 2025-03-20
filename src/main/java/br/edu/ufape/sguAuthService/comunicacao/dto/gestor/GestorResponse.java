package br.edu.ufape.sguAuthService.comunicacao.dto.gestor;

import br.edu.ufape.sguAuthService.comunicacao.dto.usuario.UsuarioResponse;
import br.edu.ufape.sguAuthService.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class GestorResponse extends UsuarioResponse {
    String siape;

    public GestorResponse(Usuario usuario, ModelMapper modelMapper){
        if (usuario == null) throw new IllegalArgumentException("Gestor não pode ser nulo");
        else modelMapper.map(usuario, this);
        this.siape = usuario.getGestor().getSiape();
    }
}
