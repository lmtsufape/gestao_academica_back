package br.edu.ufape.sgi.comunicacao.dto.tecnico;


import br.edu.ufape.sgi.comunicacao.dto.usuario.UsuarioResponse;
import br.edu.ufape.sgi.models.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter @NoArgsConstructor
public class TecnicoResponse extends UsuarioResponse {
    String siape;

    public TecnicoResponse(Usuario usuario, ModelMapper modelMapper){
        if (usuario == null) throw new IllegalArgumentException("Aluno não pode ser nulo");
        else modelMapper.map(usuario, this);
        this.siape = usuario.getTecnico().getSiape();
    }
}
