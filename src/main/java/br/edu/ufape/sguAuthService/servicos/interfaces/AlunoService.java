package br.edu.ufape.sguAuthService.servicos.interfaces;

import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.AlunoNotFoundException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sguAuthService.models.Usuario;

import java.util.List;

public interface AlunoService {

    List<Usuario> listarAlunos();

    Usuario buscarAluno(Long id, boolean isAdm, String sessionId) throws AlunoNotFoundException, UsuarioNotFoundException;
}
