package br.edu.ufape.sguAuthService.servicos;

import br.edu.ufape.sguAuthService.dados.UsuarioRepository;
import br.edu.ufape.sguAuthService.exceptions.accessDeniedException.GlobalAccessDeniedException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.GestorNotFoundException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sguAuthService.models.Gestor;
import br.edu.ufape.sguAuthService.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service @RequiredArgsConstructor
public class GestorService implements br.edu.ufape.sguAuthService.servicos.interfaces.GestorService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarGestores() {return usuarioRepository.findUsuariosGestores();}

    @Override
    public Usuario buscarGestor(Long id, boolean isAdm, String sessionId) throws GestorNotFoundException, UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
        if(!isAdm && !usuario.getKcId().equals(sessionId)) {
            throw new GlobalAccessDeniedException("Você não tem permissão para acessar este recurso");
        }
        if (usuario.getPerfis().stream().noneMatch(perfil -> perfil instanceof Gestor)) {
            throw new GestorNotFoundException();
        }
        return usuario;
    }
}
