package br.edu.ufape.sguAuthService.comunicacao.dto.unidadeAdministrativa;


import br.edu.ufape.sguAuthService.models.UnidadeAdministrativa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "O tipo de unidade administrativa é obrigatório")
    private Long tipoUnidadeAdministrativaId;

    private Long unidadePaiId; //esse atributo é para definir a unidade pai da unidade que está sendo criada


    public UnidadeAdministrativa convertToEntity(
            UnidadeAdministrativaRequest unidadeAdministrativaRequest,
            ModelMapper modelMapper) {

        modelMapper.typeMap(UnidadeAdministrativaRequest.class, UnidadeAdministrativa.class)
                .addMappings(mapper -> {
                    mapper.skip(UnidadeAdministrativa::setUnidadePai); // Ignora unidadePai
                    mapper.skip(UnidadeAdministrativa::setId); // Garante que unidadePaiId não seja mapeado para id
                });

        return modelMapper.map(unidadeAdministrativaRequest, UnidadeAdministrativa.class);
    }
}
