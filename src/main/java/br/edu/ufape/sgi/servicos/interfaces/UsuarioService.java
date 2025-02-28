package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario salvar(Usuario usuario);

    Usuario editarUsuario(String idSessao, Usuario novoUsuario) throws UsuarioNotFoundException;

    Usuario buscarUsuario(Long id, boolean isAdmin, String sessionId) throws UsuarioNotFoundException;

    Usuario buscarUsuarioPorKcId(String kcId) throws UsuarioNotFoundException;

    List<Usuario> listarUsuarios();

    void deletarUsuario(String sessionId) throws UsuarioNotFoundException;

    void deletarUsuarioKcId(String kcId) throws UsuarioNotFoundException;
}
