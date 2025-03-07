package br.edu.ufape.sgi.servicos.interfaces;

import java.util.List;

import br.edu.ufape.sgi.exceptions.TipoUnidadeAdministrativaDuplicadoException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.TipoUnidadeAdministrativaNotFoundException;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;

public interface TipoUnidadeAdministrativaService {

    TipoUnidadeAdministrativa salvar(TipoUnidadeAdministrativa tipoUnidadeAdministrativa) throws TipoUnidadeAdministrativaDuplicadoException;

    TipoUnidadeAdministrativa buscar(Long id) throws TipoUnidadeAdministrativaNotFoundException;
    
    List<TipoUnidadeAdministrativa> listar();

    TipoUnidadeAdministrativa editar(Long id, TipoUnidadeAdministrativa novoTipoUnidadeAdministrativa) throws TipoUnidadeAdministrativaNotFoundException;

    void deletar(Long id) throws TipoUnidadeAdministrativaNotFoundException;
}
