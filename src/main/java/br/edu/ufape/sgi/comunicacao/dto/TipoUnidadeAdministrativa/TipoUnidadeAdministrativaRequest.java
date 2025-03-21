package br.edu.ufape.sgi.comunicacao.dto.TipoUnidadeAdministrativa;
import org.modelmapper.ModelMapper;

import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TipoUnidadeAdministrativaRequest {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    public TipoUnidadeAdministrativa convertToEntity(TipoUnidadeAdministrativaRequest tipoUnidadeAdministrativaRequest, ModelMapper modelMapper) {
        return modelMapper.map(tipoUnidadeAdministrativaRequest, TipoUnidadeAdministrativa.class);
    }
}
