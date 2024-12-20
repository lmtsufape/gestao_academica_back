package br.edu.ufape.sgi.comunicacao.dto.professor;

import br.edu.ufape.sgi.comunicacao.dto.curso.CursoResponse;
import br.edu.ufape.sgi.comunicacao.dto.usuario.UsuarioResponse;
import br.edu.ufape.sgi.models.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter @NoArgsConstructor
public class ProfessorResponse extends UsuarioResponse {
    String siape;
    Set<CursoResponse> cursos;

    public ProfessorResponse(Usuario usuario, ModelMapper modelMapper){
        if (usuario == null) throw new IllegalArgumentException("Aluno não pode ser nulo");
        else modelMapper.map(usuario, this);
        this.siape = usuario.getProfessor().getSiape();
        this.cursos = usuario.getProfessor().getCursos().stream().map(curso -> new CursoResponse(curso, modelMapper)).collect(Collectors.toSet());
    }

}
