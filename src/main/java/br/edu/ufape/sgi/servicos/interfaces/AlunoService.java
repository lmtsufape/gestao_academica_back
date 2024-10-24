package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.exceptions.notFoundExceptions.AlunoNotFoundException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.Usuario;

import java.util.List;

public interface AlunoService {

    List<Usuario> listarAlunos();

    Usuario buscarAluno(Long id, boolean isAdm, String sessionId) throws AlunoNotFoundException, UsuarioNotFoundException;
}
