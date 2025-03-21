package br.edu.ufape.sguAuthService.servicos.interfaces;

import java.util.List;

import br.edu.ufape.sguAuthService.exceptions.TipoUnidadeAdministrativaDuplicadoException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.TipoUnidadeAdministrativaNotFoundException;
import br.edu.ufape.sguAuthService.models.TipoUnidadeAdministrativa;

public interface TipoUnidadeAdministrativaService {

    TipoUnidadeAdministrativa salvar(TipoUnidadeAdministrativa tipoUnidadeAdministrativa) throws TipoUnidadeAdministrativaDuplicadoException;

    TipoUnidadeAdministrativa buscar(Long id) throws TipoUnidadeAdministrativaNotFoundException;
    
    List<TipoUnidadeAdministrativa> listar();

    TipoUnidadeAdministrativa editar(Long id, TipoUnidadeAdministrativa novoTipoUnidadeAdministrativa) throws TipoUnidadeAdministrativaNotFoundException;

    void deletar(Long id) throws TipoUnidadeAdministrativaNotFoundException;
}
