package br.edu.ufape.sgi.comunicacao.dto.aluno;

import br.edu.ufape.sgi.comunicacao.dto.usuario.UsuarioResponse;
import br.edu.ufape.sgi.models.Aluno;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter @NoArgsConstructor
public class AlunoResponse extends UsuarioResponse {
    String matricula;
    String curso;

    public AlunoResponse(Aluno aluno, ModelMapper modelMapper){
        if (aluno == null) throw new IllegalArgumentException("Aluno não pode ser nulo");
        else modelMapper.map(aluno, this);
    }
}
