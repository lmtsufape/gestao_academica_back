package br.edu.ufape.sgi.comunicacao.dto.curso;

import br.edu.ufape.sgi.models.Curso;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Getter @Setter
public class CursoResponse {
    Long id;
    String nome;

    public CursoResponse(Curso curso, ModelMapper modelMapper){
        if (curso == null) throw new IllegalArgumentException("Curso não pode ser nulo");
        else modelMapper.map(curso, this);
    }
}
