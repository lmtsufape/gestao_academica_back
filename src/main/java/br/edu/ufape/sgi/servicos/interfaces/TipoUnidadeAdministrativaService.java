package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.exceptions.tipoUnidadeAdministrativa.TipoUnidadeAdministrativaNotFoundException;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;

import java.util.List;

public interface TipoUnidadeAdministrativaService {


    TipoUnidadeAdministrativa salvar(TipoUnidadeAdministrativa tipoUnidadeAdministrativa);

    TipoUnidadeAdministrativa buscarTipoUnidadeAdministrativa(Long id) throws TipoUnidadeAdministrativaNotFoundException;

    List<TipoUnidadeAdministrativa> listarTiposUnidadeAdministrativa();

    void deletarTipoUnidadeAdministrativa(Long id) throws TipoUnidadeAdministrativaNotFoundException;
}
