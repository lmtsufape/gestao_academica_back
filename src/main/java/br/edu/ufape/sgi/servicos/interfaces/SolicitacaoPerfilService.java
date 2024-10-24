package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.exceptions.SolicitacaoDuplicadaException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.SolicitacaoNotFoundException;
import br.edu.ufape.sgi.models.SolicitacaoPerfil;
import jakarta.transaction.Transactional;

import java.util.List;

public interface SolicitacaoPerfilService {
    @Transactional
    SolicitacaoPerfil solicitarPerfil(SolicitacaoPerfil solicitacaoPerfil) throws SolicitacaoDuplicadaException;

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
