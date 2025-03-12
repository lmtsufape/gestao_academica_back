package br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa;


import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeAdministrativaRequest {
    private Long tipoId;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O código é obrigatório")
    private String codigo;

    @NotNull(message = "O tipo de unidade administrativa é obrigatório")
    private Long tipoUnidadeAdministrativaId;

    public UnidadeAdministrativa convertToEntity(UnidadeAdministrativaRequest unidadeAdministrativaRequest, ModelMapper modelMapper) {
        return modelMapper.map(unidadeAdministrativaRequest, UnidadeAdministrativa.class);
    }

    public Long getTipoId() {
        return tipoId;
    }
}
