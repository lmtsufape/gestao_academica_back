package br.edu.ufape.sgi.servicos;

import br.edu.ufape.sgi.dados.UsuarioRepository;
import br.edu.ufape.sgi.exceptions.accessDeniedException.GlobalAccessDeniedException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.ProfessorNotFoundException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.Enums.TipoPerfil;
import br.edu.ufape.sgi.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService implements br.edu.ufape.sgi.servicos.interfaces.ProfessorService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarProfessores() {
        return usuarioRepository.findUsuariosProfessores();
    }


    @Override
    public Usuario buscarProfessor(Long id, boolean isAdm, String sessionId) throws ProfessorNotFoundException, UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
        if(!isAdm && !usuario.getKcId().equals(sessionId)) {
            throw new GlobalAccessDeniedException("Você não tem permissão para acessar este recurso");
        }
        if(usuario.getPerfis().stream().noneMatch(perfil -> perfil.getTipo().equals(TipoPerfil.PROFESSOR))) {
            throw new ProfessorNotFoundException();
        }
        return usuario;
    }
}
