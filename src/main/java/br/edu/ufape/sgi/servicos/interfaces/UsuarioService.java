package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.exceptions.usuario.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario salvar(Usuario usuario);

    Usuario buscarUsuario(Long id) throws UsuarioNotFoundException;

    List<Usuario> listarUsuarios();

    void deletarUsuario(Long id) throws UsuarioNotFoundException;
}
