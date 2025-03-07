package br.edu.ufape.sgi.comunicacao.dto.TipoUnidadeAdministrativa;

import org.modelmapper.ModelMapper;

import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TipoUnidadeAdministrativaResponse {
    Long id;
    String nome;
    public TipoUnidadeAdministrativaResponse(TipoUnidadeAdministrativa tipoUnidadeAdministrativa, ModelMapper modelMapper){
        if (tipoUnidadeAdministrativa == null) throw new IllegalArgumentException("TipoUnidadeAdministrativa não pode ser nulo");
        else modelMapper.map(tipoUnidadeAdministrativa, this);
    }
}
