package br.edu.ufape.sguAuthService.comunicacao.dto.aluno;

import br.edu.ufape.sguAuthService.comunicacao.dto.curso.CursoResponse;
import br.edu.ufape.sguAuthService.comunicacao.dto.usuario.UsuarioResponse;
import br.edu.ufape.sguAuthService.models.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter @NoArgsConstructor
public class AlunoResponse extends UsuarioResponse {
    String matricula;
    CursoResponse curso;

    public AlunoResponse(Usuario usuario, ModelMapper modelMapper){
        if (usuario == null) throw new IllegalArgumentException("Aluno não pode ser nulo");
        else modelMapper.map(usuario, this);
        this.matricula = usuario.getAluno().getMatricula();
        this.curso = new CursoResponse(usuario.getAluno().getCurso(), modelMapper);
    }
}
