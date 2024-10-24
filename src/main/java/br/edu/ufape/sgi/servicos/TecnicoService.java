package br.edu.ufape.sgi.servicos;

import br.edu.ufape.sgi.dados.UsuarioRepository;
import br.edu.ufape.sgi.exceptions.accessDeniedException.GlobalAccessDeniedException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.TecnicoNotFoundException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.Enums.TipoPerfil;
import br.edu.ufape.sgi.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnicoService implements br.edu.ufape.sgi.servicos.interfaces.TecnicoService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getTecnicos(){
        return usuarioRepository.findUsuariosTecnicos();
    }


    @Override
    public Usuario buscarTecnico(Long id, boolean isAdm, String sessionId) throws TecnicoNotFoundException, UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
        if(!isAdm && !usuario.getKcId().equals(sessionId)) {
            throw new GlobalAccessDeniedException("Você não tem permissão para acessar este recurso");
        }
        if(usuario.getPerfis().stream().noneMatch(perfil -> perfil.getTipo().equals(TipoPerfil.TECNICO))) {
            throw new TecnicoNotFoundException();
        }
        return usuario;
    }



}
