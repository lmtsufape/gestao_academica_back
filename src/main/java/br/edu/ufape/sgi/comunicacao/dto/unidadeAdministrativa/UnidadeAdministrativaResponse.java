package br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufape.sgi.comunicacao.dto.TipoUnidadeAdministrativa.TipoUnidadeAdministrativaResponse;
import org.modelmapper.ModelMapper;

import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import lombok.AllArgsConstructor;
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