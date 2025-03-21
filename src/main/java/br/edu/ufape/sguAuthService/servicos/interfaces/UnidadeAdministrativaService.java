package br.edu.ufape.sguAuthService.servicos.interfaces;

import br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa.UnidadeAdministrativaNotFoundException;
import br.edu.ufape.sguAuthService.models.UnidadeAdministrativa;

import java.util.List;

public interface UnidadeAdministrativaService {

    UnidadeAdministrativa salvar(UnidadeAdministrativa unidadeAdministrativa, Long paiId) throws UnidadeAdministrativaNotFoundException ;

    UnidadeAdministrativa buscarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException;

    List<UnidadeAdministrativa> listarUnidadesAdministrativas();

    List<UnidadeAdministrativa> montarArvore();

    List<UnidadeAdministrativa> listarUnidadesFilhas(Long id);

    void deletarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException;

    UnidadeAdministrativa editarUnidadeAdministrativa(UnidadeAdministrativa unidadeAdministrativa, Long id) throws UnidadeAdministrativaNotFoundException;

//    void adicionarGestor(Long unidadeId, Long usuarioId);
}
