package br.edu.ufape.sguAuthService.servicos.interfaces;

import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.ProfessorNotFoundException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sguAuthService.models.Usuario;

import java.util.List;

public interface ProfessorService {
    List<Usuario> listarProfessores();

    Usuario buscarProfessor(Long id, boolean isAdm, String sessionId) throws ProfessorNotFoundException, UsuarioNotFoundException;
}
