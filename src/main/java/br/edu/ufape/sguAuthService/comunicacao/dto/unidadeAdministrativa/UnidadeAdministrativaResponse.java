package br.edu.ufape.sguAuthService.comunicacao.dto.unidadeAdministrativa;

import br.edu.ufape.sguAuthService.models.UnidadeAdministrativa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeAdministrativaResponse {
    private Long id;
    private String nome;
    private String codigo;
    private TipoUnidadeAdministrativaResponse tipoUnidadeAdministrativa; //Trocar para a entidade TipoUnidadeAdministrativa
    private List<UnidadeAdministrativaResponse> unidadesFilhas = new ArrayList<>();




    public UnidadeAdministrativaResponse(UnidadeAdministrativa unidadeAdministrativa, ModelMapper modelMapper) {
        if (unidadeAdministrativa == null) throw new IllegalArgumentException("Unidade Administrativa não pode ser nula");
        modelMapper.map(unidadeAdministrativa, this);

    }
}
