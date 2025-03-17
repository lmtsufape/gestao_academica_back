package br.edu.ufape.sguAuthService.servicos.interfaces;

import br.edu.ufape.sguAuthService.exceptions.SolicitacaoDuplicadaException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.SolicitacaoNotFoundException;
import br.edu.ufape.sguAuthService.models.Documento;
import br.edu.ufape.sguAuthService.models.Perfil;
import br.edu.ufape.sguAuthService.models.SolicitacaoPerfil;
import br.edu.ufape.sguAuthService.models.Usuario;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface SolicitacaoPerfilService {
    @Transactional
    SolicitacaoPerfil solicitarPerfil(Perfil perfil, Usuario solicitante, List<Documento> documentos) throws SolicitacaoDuplicadaException;

    SolicitacaoPerfil buscarSolicitacao(Long id) throws SolicitacaoNotFoundException;

    List<SolicitacaoPerfil> buscarSolicitacoesUsuario(String sessionId);

    List<SolicitacaoPerfil> buscarSolicitacoesPorId(Long id);

    List<SolicitacaoPerfil> listarSolicitacoes();

    List<SolicitacaoPerfil> listarSolicitacoesPendentes();

    @Transactional
    SolicitacaoPerfil aceitarSolicitacao(Long id, SolicitacaoPerfil parecer) throws SolicitacaoNotFoundException;

    @Transactional
    SolicitacaoPerfil rejeitarSolicitacao(Long id, SolicitacaoPerfil parecer) throws SolicitacaoNotFoundException;
}
