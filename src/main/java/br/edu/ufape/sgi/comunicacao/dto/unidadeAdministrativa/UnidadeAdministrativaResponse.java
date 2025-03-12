package br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa;

import org.modelmapper.ModelMapper;

import br.edu.ufape.sgi.comunicacao.dto.TipoUnidadeAdministrativa.TipoUnidadeAdministrativaResponse;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeAdministrativaResponse {
    private Long id;
    private String nome;
    private String codigo;
    private String tipoUnidadeAdministrativa;

    public UnidadeAdministrativaResponse() {
    }

    public UnidadeAdministrativaResponse(UnidadeAdministrativa unidadeAdministrativa, ModelMapper modelMapper) {
        if (unidadeAdministrativa == null) throw new IllegalArgumentException("Unidade Administrativa não pode ser nula");
        modelMapper.map(unidadeAdministrativa, this);
        this.tipoUnidadeAdministrativa = unidadeAdministrativa.getTipoUnidadeAdministrativa().getNome();
    }

}