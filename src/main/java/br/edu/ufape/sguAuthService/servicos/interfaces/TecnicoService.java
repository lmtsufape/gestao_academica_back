package br.edu.ufape.sguAuthService.servicos.interfaces;

import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.TecnicoNotFoundException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sguAuthService.models.Usuario;

import java.util.List;

public interface TecnicoService {
    List<Usuario> getTecnicos();

    Usuario buscarTecnico(Long id, boolean isAdm, String sessionId) throws TecnicoNotFoundException, UsuarioNotFoundException;
}
