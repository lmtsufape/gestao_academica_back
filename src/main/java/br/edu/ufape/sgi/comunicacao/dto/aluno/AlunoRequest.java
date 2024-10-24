package br.edu.ufape.sgi.comunicacao.dto.aluno;



import br.edu.ufape.sgi.exceptions.notFoundExceptions.CursoNotFoundException;
import br.edu.ufape.sgi.fachada.Fachada;
import br.edu.ufape.sgi.models.Aluno;
import br.edu.ufape.sgi.models.Curso;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AlunoRequest {

    @NotBlank(message = "A matrícula é obrigatória")
    private String matricula;
    @NotBlank(message = "O curso é obrigatório")
    private Long cursoId;

    @NotBlank(message = "Os documentos são obrigatórios")
    private MultipartFile[] documentos;

    public Aluno convertToEntity( ModelMapper modelMapper, Fachada fachada) throws CursoNotFoundException {
        Curso curso = fachada.buscarCurso(this.getCursoId());
        modelMapper.typeMap(AlunoRequest.class, Aluno.class).addMappings(mapper -> {
            mapper.skip(Aluno::setId);  // Ignora o campo ID
        });
        Aluno aluno = modelMapper.map(this, Aluno.class);
        aluno.setCurso(curso);
        return aluno;
    }

}
