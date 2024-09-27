package br.edu.ufape.sgi.fachada;


import br.edu.ufape.sgi.comunicacao.dto.auth.TokenResponse;
import br.edu.ufape.sgi.exceptions.aluno.AlunoNotFoundException;
import br.edu.ufape.sgi.exceptions.auth.KeycloakAuthenticationException;
import br.edu.ufape.sgi.exceptions.usuario.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.Aluno;
import br.edu.ufape.sgi.models.Usuario;
import br.edu.ufape.sgi.servicos.KeycloakService;
import br.edu.ufape.sgi.servicos.interfaces.AlunoService;
import br.edu.ufape.sgi.servicos.interfaces.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @RequiredArgsConstructor

public class Fachada {
    private final AlunoService alunoService;
    private final UsuarioService usuarioService;
    private final KeycloakService keycloakService;

    // ================== Auth ================== //
    public TokenResponse login(String username, String password) {
        return keycloakService.login(username, password);
    }

    public TokenResponse refresh(String refreshToken) {
        return keycloakService.refreshToken(refreshToken);
    }

    // ================== Aluno ================== //
    public Aluno salvarUsuario(Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    public Aluno buscarAluno(Long id) throws AlunoNotFoundException { return alunoService.buscarAluno(id);
    }
    public List<Aluno> listarAlunos() {return alunoService.listarAlunos();}

    public void deletarAluno(Long id) throws AlunoNotFoundException {alunoService.deletarAluno(id);}

    // ================== Usuario ================== //
    @Transactional
    public Usuario salvarUsuario(Usuario usuario, String senha) {
        String userKcId = null;
        try {
            Response keycloakResponse = keycloakService.createUser(usuario.getEmail(), senha, "visitante");
            if (keycloakResponse.getStatus() == 201) {
                userKcId = keycloakService.getUserId(usuario.getEmail());
                usuario.setKcId(userKcId);
                return usuarioService.salvar(usuario);

            } else {
                throw new RuntimeException("Falha ao criar o usuário no Keycloak. Status: " + keycloakResponse.getStatus());
            }
        } catch (KeycloakAuthenticationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (DataIntegrityViolationException e) {
            if (userKcId != null) {
                keycloakService.deleteUser(userKcId);
            }
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            if (userKcId != null) {
                keycloakService.deleteUser(userKcId);
            }
            throw new RuntimeException("Erro inesperado ao salvarUsuario usuário: " + e.getMessage(), e);
        }
    }

    public Usuario editarUsuario(String idSessao, Usuario novoUsuario) throws UsuarioNotFoundException {
        return usuarioService.editarUsuario(idSessao, novoUsuario);
    }


    public Usuario buscarUsuario(Long id) throws UsuarioNotFoundException {return usuarioService.buscarUsuario(id);}

    public List<Usuario> listarUsuarios() {return usuarioService.listarUsuarios();}

    public void deletarUsuario(String idSessao) throws UsuarioNotFoundException {
        try {
            keycloakService.deleteUser(idSessao);
        } catch (KeycloakAuthenticationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        usuarioService.deletarUsuario(idSessao);}
}
