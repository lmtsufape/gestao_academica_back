package br.edu.ufape.sguAuthService.servicos.interfaces;

import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.GestorNotFoundException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sguAuthService.models.Usuario;

import java.util.List;

public interface GestorService {
    List<Usuario> listarGestores();

    Usuario buscarGestor(Long id, boolean isAdm, String sessionId) throws GestorNotFoundException, UsuarioNotFoundException;
}
