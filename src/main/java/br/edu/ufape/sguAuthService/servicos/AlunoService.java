package br.edu.ufape.sguAuthService.servicos;


import br.edu.ufape.sguAuthService.dados.UsuarioRepository;
import br.edu.ufape.sguAuthService.exceptions.accessDeniedException.GlobalAccessDeniedException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.AlunoNotFoundException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sguAuthService.models.Aluno;
import br.edu.ufape.sguAuthService.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor

public class AlunoService implements br.edu.ufape.sguAuthService.servicos.interfaces.AlunoService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarAlunos() {
        return usuarioRepository.findUsuariosAlunos();
    }

    @Override
    public Usuario buscarAluno(Long id, boolean isAdm, String sessionId) throws AlunoNotFoundException, UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
        if(!isAdm && !usuario.getKcId().equals(sessionId)) {
            throw new GlobalAccessDeniedException("Você não tem permissão para acessar este recurso");
        }
        if (usuario.getPerfis().stream().noneMatch(perfil -> perfil instanceof Aluno)) {
            throw new AlunoNotFoundException();
        }
        return usuario;
    }

}
