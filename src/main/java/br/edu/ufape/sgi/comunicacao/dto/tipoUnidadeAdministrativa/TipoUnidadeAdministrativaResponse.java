package br.edu.ufape.sgi.comunicacao.dto.tipoUnidadeAdministrativa;

import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class TipoUnidadeAdministrativaResponse {
    public TipoUnidadeAdministrativaResponse(TipoUnidadeAdministrativa tipo, ModelMapper modelMapper) {
        if (tipo == null) throw new IllegalArgumentException("Tipo Unidade Administrativa não pode ser nulo");
        else modelMapper.map(tipo, this);
    }
}
