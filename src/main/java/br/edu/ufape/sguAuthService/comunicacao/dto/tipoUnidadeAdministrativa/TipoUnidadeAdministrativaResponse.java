package br.edu.ufape.sguAuthService.comunicacao.dto.tipoUnidadeAdministrativa;



import br.edu.ufape.sguAuthService.models.TipoUnidadeAdministrativa;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TipoUnidadeAdministrativaResponse {
    Long id;
    String nome;
    
    public TipoUnidadeAdministrativaResponse(TipoUnidadeAdministrativa tipoUnidadeAdministrativa, ModelMapper modelMapper){
        if (tipoUnidadeAdministrativa == null) throw new IllegalArgumentException("TipoUnidadeAdministrativa não pode ser nulo");
        else{
             modelMapper.map(tipoUnidadeAdministrativa, this);
        }
    }
}
