package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.exceptions.unidadeAdministrativa.UnidadeAdministrativaNotFoundException;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;

import java.util.List;

public interface UnidadeAdministrativaService {

    UnidadeAdministrativa salvar(UnidadeAdministrativa unidadeAdministrativa, Long tipoId)throws UnidadeAdministrativaNotFoundException ;

    UnidadeAdministrativa buscarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException;

    List<UnidadeAdministrativa> listarUnidadesAdministrativas();

    void deletarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException;
}
