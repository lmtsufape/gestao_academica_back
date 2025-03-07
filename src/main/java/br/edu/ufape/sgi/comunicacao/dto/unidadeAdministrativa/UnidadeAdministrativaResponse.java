package br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa;

import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class UnidadeAdministrativaResponse {
    private Long id;
    private String nome;
    private String codigo;
    private String tipoUnidadeAdministrativa;

    public UnidadeAdministrativaResponse(UnidadeAdministrativa unidadeAdministrativa, ModelMapper modelMapper) {
        if (unidadeAdministrativa == null) throw new IllegalArgumentException("Unidade Administrativa não pode ser nula");
        else modelMapper.map(unidadeAdministrativa, this);
    }

}