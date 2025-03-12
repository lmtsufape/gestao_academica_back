package br.edu.ufape.sgi.comunicacao.dto.TipoUnidadeAdministrativa;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.edu.ufape.sgi.comunicacao.dto.unidadeAdministrativa.UnidadeAdministrativaResponse;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class TipoUnidadeAdministrativaResponse {
    Long id;
    String nome;
    private List<UnidadeAdministrativaResponse> unidadeAdministrativas;
    public TipoUnidadeAdministrativaResponse() {
    }
    
    public TipoUnidadeAdministrativaResponse(TipoUnidadeAdministrativa tipoUnidadeAdministrativa, ModelMapper modelMapper){
        if (tipoUnidadeAdministrativa == null) throw new IllegalArgumentException("TipoUnidadeAdministrativa não pode ser nulo");
        else{
             modelMapper.map(tipoUnidadeAdministrativa, this);
             this.unidadeAdministrativas = tipoUnidadeAdministrativa.getUnidadeAdministrativa().stream()
                    .map(unidade -> new UnidadeAdministrativaResponse(unidade, modelMapper))
                    .collect(Collectors.toList());
        }
    }
}
