package br.edu.ufape.sgi.comunicacao.dto.aluno;


import br.edu.ufape.sgi.comunicacao.dto.usuario.UsuarioRequest;
import br.edu.ufape.sgi.models.Aluno;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AlunoRequest extends UsuarioRequest {

    @NotBlank(message = "A matrícula é obrigatória")
    private String matricula;
    private String curso;

    public Aluno convertToEntity(AlunoRequest alunoRequest, ModelMapper modelMapper) {
        return modelMapper.map(alunoRequest, Aluno.class);
    }

}
