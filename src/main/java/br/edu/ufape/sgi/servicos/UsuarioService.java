package br.edu.ufape.sgi.servicos;

import br.edu.ufape.sgi.dados.UsuarioRepository;
import br.edu.ufape.sgi.exceptions.ExceptionUtil;

import br.edu.ufape.sgi.exceptions.usuario.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.Usuario;

import br.edu.ufape.sgi.models.Visitante;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class UsuarioService implements br.edu.ufape.sgi.servicos.interfaces.UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvar(Usuario usuario) {
        try {
            Visitante visitante = new Visitante();
            usuario.adicionarPerfil(visitante);
            return usuarioRepository.save(usuario);
        }catch (DataIntegrityViolationException e){
            ExceptionUtil.handleDataIntegrityViolationException(e);
            return null;
        }
    }

    @Override
    public Usuario buscarUsuario(Long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void deletarUsuario(Long id) throws UsuarioNotFoundException {
        if (!usuarioRepository.existsById(id)) throw new UsuarioNotFoundException();
        usuarioRepository.deleteById(id);
    }



}
