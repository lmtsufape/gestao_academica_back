package br.edu.ufape.sgi.fachada;


import br.edu.ufape.sgi.comunicacao.dto.auth.TokenResponse;
import br.edu.ufape.sgi.exceptions.CursoDuplicadoException;
import br.edu.ufape.sgi.exceptions.ExceptionUtil;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.AlunoNotFoundException;
import br.edu.ufape.sgi.exceptions.auth.KeycloakAuthenticationException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.CursoNotFoundException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.Aluno;
import br.edu.ufape.sgi.models.Curso;
import br.edu.ufape.sgi.models.Usuario;
import br.edu.ufape.sgi.servicos.interfaces.AlunoService;
import br.edu.ufape.sgi.servicos.interfaces.CursoService;
import br.edu.ufape.sgi.servicos.interfaces.KeycloakServiceInterface;
import br.edu.ufape.sgi.servicos.interfaces.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @RequiredArgsConstructor

public class Fachada {
    private final AlunoService alunoService;
    private final UsuarioService usuarioService;
    private final KeycloakServiceInterface keycloakService;
    private final CursoService cursoService;

    // ================== Auth ================== //
    public TokenResponse login(String username, String password) {
        return keycloakService.login(username, password);
    }

    public TokenResponse refresh(String refreshToken) {
        return keycloakService.refreshToken(refreshToken);
    }

    public void logout(String accessToken, String refreshToken) {
        keycloakService.logout(accessToken, refreshToken);
    }

    // ================== Aluno ================== //


    public Aluno buscarAluno(Long id) throws AlunoNotFoundException { return alunoService.buscarAluno(id);
    }
    public List<Aluno> listarAlunos() {return alunoService.listarAlunos();}

    public void deletarAluno(Long id) throws AlunoNotFoundException {alunoService.deletarAluno(id);}

    // ================== Usuario ================== //
    @Transactional
    public Usuario salvarUsuario(Usuario usuario, String senha) {
        String userKcId = null;
            keycloakService.createUser(usuario.getEmail(), senha, "visitante");
            try {
                userKcId = keycloakService.getUserId(usuario.getEmail());
                usuario.setKcId(userKcId);
                return usuarioService.salvar(usuario);
            }catch (DataIntegrityViolationException e){
                keycloakService.deleteUser(userKcId);
                ExceptionUtil.handleDataIntegrityViolationException(e);
                throw e;
            }catch (Exception e){
                keycloakService.deleteUser(userKcId);
                throw new RuntimeException("Ocorreu um erro inesperado ao salvar o usuário: "+ e.getMessage(), e);
            }
    }

    public Usuario editarUsuario(String idSessao, Usuario novoUsuario) throws UsuarioNotFoundException {
        return usuarioService.editarUsuario(idSessao, novoUsuario);
    }

    public Usuario buscarUsuarioPorKcId(String kcId) throws UsuarioNotFoundException {return usuarioService.buscarUsuarioPorKcId(kcId);}

    public Usuario buscarUsuario(Long id) throws UsuarioNotFoundException {return usuarioService.buscarUsuario(id);}

    public List<Usuario> listarUsuarios() {return usuarioService.listarUsuarios();}

    public void deletarUsuario(String idSessao) throws UsuarioNotFoundException {
        try {
            keycloakService.deleteUser(idSessao);
        } catch (KeycloakAuthenticationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        usuarioService.deletarUsuario(idSessao);}

    // ================== Curso ================== //
    public Curso salvarCurso(Curso curso) throws CursoDuplicadoException {
        return cursoService.salvar(curso);
    }

    public Curso buscarCurso(Long id) throws CursoNotFoundException {
        return cursoService.buscar(id);
    }

    public List<Curso> listarCursos() {
        return cursoService.listar();
    }

    public Curso editarCurso(Long id, Curso novoCurso) throws CursoNotFoundException, CursoDuplicadoException {
        return cursoService.editar(id, novoCurso);
    }

    public void deletarCurso(Long id) throws CursoNotFoundException {
        cursoService.deletar(id);
    }
}
