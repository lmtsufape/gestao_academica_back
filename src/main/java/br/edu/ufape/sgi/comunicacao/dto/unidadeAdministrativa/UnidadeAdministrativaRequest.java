package br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa;

import br.edu.ufape.sgi.comunicacao.dto.aluno.AlunoRequest;
import br.edu.ufape.sgi.models.Aluno;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor

public class UnidadeAdministrativaRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O código é obrigatório")
    private String codigo;

    public UnidadeAdministrativa convertToEntity(UnidadeAdministrativaRequest unidadeAdministrativaRequest, ModelMapper modelMapper) {
        return modelMapper.map(unidadeAdministrativaRequest, UnidadeAdministrativa.class);
    }
}
