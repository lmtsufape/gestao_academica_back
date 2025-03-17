package br.edu.ufape.sguAuthService.comunicacao.dto.solicitacaoPerfil;

import br.edu.ufape.sguAuthService.models.SolicitacaoPerfil;
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
