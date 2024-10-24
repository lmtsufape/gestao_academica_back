package br.edu.ufape.sgi.comunicacao.dto.professor;


import br.edu.ufape.sgi.exceptions.notFoundExceptions.CursoNotFoundException;
import br.edu.ufape.sgi.fachada.Fachada;
import br.edu.ufape.sgi.models.Curso;
import br.edu.ufape.sgi.models.Professor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequest {

    @NotBlank(message = "O SIAPE é obrigatório")
    private String siape;

    @NotEmpty(message = "A lista de cursos é obrigatória")
    private List<Long> cursoIds; // Lista de IDs dos cursos

    @NotBlank(message = "Os documentos são obrigatórios")
    private MultipartFile[] documentos;

    @SneakyThrows
    public Professor convertToEntity(ModelMapper modelMapper, Fachada fachada) {
        // Recuperar a lista de cursos a partir dos IDs
        Set<Curso> cursos = cursoIds.stream()
                .map(cursoId -> {
                    try {
                        return fachada.buscarCurso(cursoId);
                    } catch (CursoNotFoundException e) {
                        // Lidar com a exceção conforme necessário, por exemplo, logar ou lançar uma RuntimeException
                        throw new RuntimeException("Curso não encontrado para o ID: " + cursoId, e);
                    }
                })
                .collect(Collectors.toSet());

        modelMapper.typeMap(ProfessorRequest.class, Professor.class)
                .addMappings(mapper -> mapper.skip(Professor::setId));
        Professor professor = modelMapper.map(this, Professor.class);
        professor.setCursos(cursos); // Define a lista de cursos no professor
        return professor;
    }

}