package br.edu.ufape.sgi.comunicacao.dto.solicitacaoPerfil;


import br.edu.ufape.sgi.comunicacao.dto.usuario.UsuarioResponse;
import br.edu.ufape.sgi.models.Enums.StatusSolicitacao;
import br.edu.ufape.sgi.models.Perfil;
import br.edu.ufape.sgi.models.SolicitacaoPerfil;
import br.edu.ufape.sgi.models.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SolicitacaoPerfilResponse {
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataSolicitacao;

    private StatusSolicitacao status;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAvaliacao;

    private String parecer;

    private Perfil perfil;

    private UsuarioResponse solicitante;

    private UsuarioResponse responsavel;

    public SolicitacaoPerfilResponse(SolicitacaoPerfil solicitacaoPerfil, ModelMapper modelMapper){
        if (solicitacaoPerfil == null) throw new IllegalArgumentException("Solicitação não pode ser nula");
        else modelMapper.map(solicitacaoPerfil, this);
    }

}
