package br.edu.ufape.sguAuthService.servicos;


import br.edu.ufape.sguAuthService.dados.SolicitacaoPerfilRepository;
import br.edu.ufape.sguAuthService.exceptions.SolicitacaoDuplicadaException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.SolicitacaoNotFoundException;
import br.edu.ufape.sguAuthService.models.Documento;
import br.edu.ufape.sguAuthService.models.Enums.StatusSolicitacao;
import br.edu.ufape.sguAuthService.models.Perfil;
import br.edu.ufape.sguAuthService.models.SolicitacaoPerfil;
import br.edu.ufape.sguAuthService.models.Usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service @RequiredArgsConstructor
public class SolicitacaoPerfilService implements br.edu.ufape.sguAuthService.servicos.interfaces.SolicitacaoPerfilService {
    private final SolicitacaoPerfilRepository solicitacaoPerfilRepository;



    @Override
    @Transactional
    public SolicitacaoPerfil solicitarPerfil(Perfil perfil, Usuario solicitante, List<Documento> documentos) throws SolicitacaoDuplicadaException {
        SolicitacaoPerfil solicitacaoPerfil = new SolicitacaoPerfil();
        solicitacaoPerfil.setPerfil(perfil);
        solicitacaoPerfil.setSolicitante(solicitante);
        solicitacaoPerfil.setDocumentos(documentos);
        solicitacaoPerfil.setStatus(StatusSolicitacao.PENDENTE);
        solicitacaoPerfil.setDataSolicitacao(LocalDateTime.now());
        List<SolicitacaoPerfil> solicitacoes = solicitacaoPerfilRepository.findBySolicitanteAndStatusIn(
                solicitacaoPerfil.getSolicitante(),
                List.of(StatusSolicitacao.PENDENTE, StatusSolicitacao.APROVADA));
        for (SolicitacaoPerfil solicitacao : solicitacoes) {
            if (solicitacao.getPerfil().getTipo().equals(solicitacaoPerfil.getPerfil().getTipo())) {
                throw new SolicitacaoDuplicadaException();
            }
        }
        return solicitacaoPerfilRepository.save(solicitacaoPerfil);

    }

    @Override
    public SolicitacaoPerfil buscarSolicitacao(Long id) throws SolicitacaoNotFoundException {
        return solicitacaoPerfilRepository.findById(id)
                .orElseThrow(SolicitacaoNotFoundException::new);
    }

    @Override
    public List<SolicitacaoPerfil> buscarSolicitacoesUsuario(String sessionId) {
        return solicitacaoPerfilRepository.findAllBySolicitante_KcId(sessionId);
    }

    @Override
    public List<SolicitacaoPerfil> buscarSolicitacoesPorId(Long id) {
        return solicitacaoPerfilRepository.findAllBySolicitante_Id(id);
    }

    @Override
    public List<SolicitacaoPerfil> listarSolicitacoes() {
        return solicitacaoPerfilRepository.findAll();
    }

    @Override
    public List<SolicitacaoPerfil> listarSolicitacoesPendentes() {
        return solicitacaoPerfilRepository.findAllByStatus(StatusSolicitacao.PENDENTE);
    }

    @Override
    @Transactional
    public SolicitacaoPerfil aceitarSolicitacao(Long id, SolicitacaoPerfil parecer) throws SolicitacaoNotFoundException {
        SolicitacaoPerfil solicitacaoPerfil = buscarSolicitacao(id);
        if (solicitacaoPerfil.getStatus() != StatusSolicitacao.PENDENTE) {
            throw new IllegalStateException("Solicitação não está pendente!");
        }
        solicitacaoPerfil.setParecer(parecer.getParecer());
        solicitacaoPerfil.setResponsavel(parecer.getResponsavel());
        solicitacaoPerfil.setDataAvaliacao(LocalDateTime.now());
        solicitacaoPerfil.setStatus(StatusSolicitacao.APROVADA);
        solicitacaoPerfil.getSolicitante().adicionarPerfil(solicitacaoPerfil.getPerfil());
        return solicitacaoPerfilRepository.save(solicitacaoPerfil);
    }

    @Override
    @Transactional
    public SolicitacaoPerfil rejeitarSolicitacao(Long id, SolicitacaoPerfil parecer) throws SolicitacaoNotFoundException {
        SolicitacaoPerfil solicitacaoPerfil = buscarSolicitacao(id);
        if (solicitacaoPerfil.getStatus() != StatusSolicitacao.PENDENTE) {
            throw new IllegalStateException("Solicitação não está pendente!");
        }
        solicitacaoPerfil.setParecer(parecer.getParecer());
        solicitacaoPerfil.setResponsavel(parecer.getResponsavel());
        solicitacaoPerfil.setStatus(StatusSolicitacao.REJEITADA);
        return solicitacaoPerfilRepository.save(solicitacaoPerfil);
    }

}
