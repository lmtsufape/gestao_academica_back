package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.exceptions.notFoundExceptions.ProfessorNotFoundException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.Usuario;

import java.util.List;

public interface ProfessorService {
    List<Usuario> listarProfessores();

    Usuario buscarProfessor(Long id, boolean isAdm, String sessionId) throws ProfessorNotFoundException, UsuarioNotFoundException;
}
