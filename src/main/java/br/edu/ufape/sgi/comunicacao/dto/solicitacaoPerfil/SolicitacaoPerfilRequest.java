package br.edu.ufape.sgi.comunicacao.dto.solicitacaoPerfil;

import br.edu.ufape.sgi.models.SolicitacaoPerfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class SolicitacaoPerfilRequest {

    private String parecer;

    public SolicitacaoPerfil convertToEntity(ModelMapper modelMapper) {
        return modelMapper.map(this, SolicitacaoPerfil.class);
    }
}
