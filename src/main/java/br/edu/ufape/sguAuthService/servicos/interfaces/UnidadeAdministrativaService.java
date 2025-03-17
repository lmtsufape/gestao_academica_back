package br.edu.ufape.sguAuthService.servicos.interfaces;

import br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa.UnidadeAdministrativaNotFoundException;
import br.edu.ufape.sguAuthService.models.UnidadeAdministrativa;

import java.util.List;

public interface UnidadeAdministrativaService {

    UnidadeAdministrativa salvar(UnidadeAdministrativa unidadeAdministrativa);

    UnidadeAdministrativa buscarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException;

    List<UnidadeAdministrativa> listarUnidadesAdministrativas();

    void deletarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException;
}
