package br.edu.ufape.sguAuthService.dados;

import br.edu.ufape.sguAuthService.models.Enums.StatusSolicitacao;
import br.edu.ufape.sguAuthService.models.SolicitacaoPerfil;
import br.edu.ufape.sguAuthService.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SolicitacaoPerfilRepository extends JpaRepository<SolicitacaoPerfil, Long> {
    List<SolicitacaoPerfil> findBySolicitanteAndStatusIn(Usuario solicitante, Collection<StatusSolicitacao> status);

    List<SolicitacaoPerfil> findAllBySolicitante_KcId(String id);

    List<SolicitacaoPerfil> findAllBySolicitante_Id(Long id);

    List<SolicitacaoPerfil> findAllByStatus(StatusSolicitacao status);

}