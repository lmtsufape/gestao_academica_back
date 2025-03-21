package br.edu.ufape.sguAuthService.comunicacao.dto.unidadeAdministrativa;

import br.edu.ufape.sguAuthService.comunicacao.dto.tipoUnidadeAdministrativa.TipoUnidadeAdministrativaResponse;
import br.edu.ufape.sguAuthService.models.UnidadeAdministrativa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

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
