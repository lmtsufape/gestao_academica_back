package br.edu.ufape.sgi.fachada;


import br.edu.ufape.sgi.comunicacao.dto.auth.TokenResponse;
import br.edu.ufape.sgi.exceptions.CursoDuplicadoException;
import br.edu.ufape.sgi.exceptions.ExceptionUtil;
import br.edu.ufape.sgi.exceptions.accessDeniedException.SolicitacaoAccessDeniedException;
import br.edu.ufape.sgi.exceptions.SolicitacaoDuplicadaException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.AlunoNotFoundException;
import br.edu.ufape.sgi.exceptions.auth.KeycloakAuthenticationException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.CursoNotFoundException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.SolicitacaoNotFoundException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.*;
import br.edu.ufape.sgi.servicos.interfaces.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Component @RequiredArgsConstructor

public class Fachada {
    private final AlunoService alunoService;
    private final UsuarioService usuarioService;
    private final KeycloakServiceInterface keycloakService;
    private final CursoService cursoService;
    private final SolicitacaoPerfilService solicitacaoPerfilService;
    private final ArmazenamentoService armazenamentoService;
    private final PerfilService perfilService;

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

    // ================== SolicitacaoPerfil ================== //
    @Transactional
    public SolicitacaoPerfil solicitarPerfil(Perfil perfil, String sessionId, MultipartFile[] arquivos) throws UsuarioNotFoundException, SolicitacaoDuplicadaException {
        SolicitacaoPerfil solicitacaoPerfil = new SolicitacaoPerfil();
        solicitacaoPerfil.setPerfil(perfilService.salvar(perfil));
        Usuario usuario = buscarUsuarioPorKcId(sessionId);
        solicitacaoPerfil.setSolicitante(usuario);
        solicitacaoPerfil.setDocumentos(armazenamentoService.salvarArquivo(arquivos));
        return solicitacaoPerfilService.solicitarPerfil(solicitacaoPerfil);
    }

    public SolicitacaoPerfil buscarSolicitacao(Long id, String sessionId) throws SolicitacaoNotFoundException {
        SolicitacaoPerfil solicitacao = solicitacaoPerfilService.buscarSolicitacao(id);
        if(!solicitacao.getSolicitante().getKcId().equals(sessionId) && !keycloakService.hasRoleAdmin(sessionId)){
            throw new SolicitacaoAccessDeniedException("Você não tem permissão para acessar essa solicitação.");
        }
        return solicitacaoPerfilService.buscarSolicitacao(id);
    }

    public List<SolicitacaoPerfil> buscarSolicitacoesUsuario(String sessionId) {
        return solicitacaoPerfilService.buscarSolicitacoesUsuario(sessionId);
    }

    public List<SolicitacaoPerfil> buscarSolicitacoesPorId(Long id) {
        return solicitacaoPerfilService.buscarSolicitacoesPorId(id);
    }

    public List<SolicitacaoPerfil> listarSolicitacoes() {
        return solicitacaoPerfilService.listarSolicitacoes();
    }

    public List<SolicitacaoPerfil> listarSolicitacoesPendentes() {
        return solicitacaoPerfilService.listarSolicitacoesPendentes();
    }

    @Transactional
    public SolicitacaoPerfil aceitarSolicitacao(Long id, SolicitacaoPerfil parecer, String sessionId) throws SolicitacaoNotFoundException, UsuarioNotFoundException {
        Usuario usuario = buscarUsuarioPorKcId(sessionId);
        parecer.setResponsavel(usuario);
        SolicitacaoPerfil solicitacaoPerfil =  solicitacaoPerfilService.aceitarSolicitacao(id, parecer);
        String tipoPerfil = solicitacaoPerfil.getPerfil().getTipo().name().toLowerCase();
        keycloakService.addRoleToUser(solicitacaoPerfil.getSolicitante().getKcId(), tipoPerfil);
        return solicitacaoPerfil;
    }

    @Transactional
    public SolicitacaoPerfil rejeitarSolicitacao(Long id, SolicitacaoPerfil parecer, String sessionId) throws SolicitacaoNotFoundException, UsuarioNotFoundException {
        Usuario usuario = buscarUsuarioPorKcId(sessionId);
        parecer.setResponsavel(usuario);
        SolicitacaoPerfil solicitacaoRejeitada = solicitacaoPerfilService.rejeitarSolicitacao(id, parecer);
        Perfil perfil = solicitacaoRejeitada.getPerfil();
        solicitacaoRejeitada.setPerfil(null);
        perfilService.deletarPerfil(perfil.getId());
        return solicitacaoRejeitada;
    }


}
