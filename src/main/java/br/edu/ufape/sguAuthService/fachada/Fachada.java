package br.edu.ufape.sguAuthService.fachada;


import br.edu.ufape.sguAuthService.exceptions.TipoUnidadeAdministrativaDuplicadoException;
import br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa.UnidadeAdministrativaNotFoundException;
import br.edu.ufape.sguAuthService.models.UnidadeAdministrativa;
import br.edu.ufape.sguAuthService.comunicacao.dto.auth.TokenResponse;
import br.edu.ufape.sguAuthService.exceptions.CursoDuplicadoException;
import br.edu.ufape.sguAuthService.exceptions.ExceptionUtil;
import br.edu.ufape.sguAuthService.exceptions.accessDeniedException.GlobalAccessDeniedException;
import br.edu.ufape.sguAuthService.exceptions.SolicitacaoDuplicadaException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.*;
import br.edu.ufape.sguAuthService.exceptions.auth.KeycloakAuthenticationException;
import br.edu.ufape.sguAuthService.models.*;
import br.edu.ufape.sguAuthService.servicos.interfaces.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Component @RequiredArgsConstructor

public class Fachada {
    private static final Logger log = LoggerFactory.getLogger(Fachada.class);
    private final AlunoService alunoService;
    private final UnidadeAdministrativaService unidadeAdministrativaService;
    private final UsuarioService usuarioService;
    private final KeycloakServiceInterface keycloakService;
    private final CursoService cursoService;
    private final SolicitacaoPerfilService solicitacaoPerfilService;
    private final ArmazenamentoService armazenamentoService;
    private final PerfilService perfilService;
    private final ProfessorService professorService;
    private final TecnicoService tecnicoService;
    private final GestorService gestorService;
    private final TipoUnidadeAdministrativaService tipoUnidadeAdministrativaService;

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

    public void resetPassword(String email) {
        keycloakService.resetPassword(email);
    }

    // ================== Aluno ================== //


    public List<Usuario> listarAlunos() {
        return alunoService.listarAlunos();
    }

    public Usuario buscarAluno(Long id, String sessionId) throws AlunoNotFoundException, UsuarioNotFoundException {
        boolean isAdmin = keycloakService.hasRoleAdmin(sessionId);
        return alunoService.buscarAluno(id, isAdmin, sessionId);
    }


    // ================== Professor ================== //
    public List<Usuario> listarProfessores() {
        return professorService.listarProfessores();
    }

    public Usuario buscarProfessor(Long id, String sessionId) throws UsuarioNotFoundException, ProfessorNotFoundException {
        boolean isAdmin = keycloakService.hasRoleAdmin(sessionId);
        return professorService.buscarProfessor(id, isAdmin, sessionId);
    }


    // ================== Tecnico ================== //

    public List<Usuario> listarTecnicos(){
        return tecnicoService.getTecnicos();
    }

    public Usuario buscarTecnico(Long id, String sessionId) throws UsuarioNotFoundException, TecnicoNotFoundException {
        boolean isAdmin = keycloakService.hasRoleAdmin(sessionId);
        return tecnicoService.buscarTecnico(id, isAdmin, sessionId);
    }

    // ================== Gestor ================== //
    public List<Usuario> listarGestores() {
        return gestorService.listarGestores();
    }

    public Usuario buscarGestor(Long id, String sessionId) throws GestorNotFoundException, UsuarioNotFoundException {
        boolean isAdmin = keycloakService.hasRoleAdmin(sessionId);
        return gestorService.buscarGestor(id, isAdmin, sessionId);
    }


    // ================== Usuario ================== //
    @Transactional
    public void removerUsuariosNaoVerificados(int horas) {
        List<UserRepresentation> usuariosNaoVerificados = keycloakService.listUnverifiedUsers();

        for (UserRepresentation user : usuariosNaoVerificados) {
            long diferenca = System.currentTimeMillis() - user.getCreatedTimestamp();
            if(diferenca > TimeUnit.HOURS.toMillis(horas)){
                try {
                    usuarioService.deletarUsuarioKcId(user.getId());
                    keycloakService.deleteUser(user.getId());
                    log.info("Usuário não verificado removido");
                }
                 catch (Exception e){
                    log.error("Erro ao deletar usuário não verificado: {}", e.getMessage());
                }
            }
        }
    }
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

    public Usuario buscarUsuario(Long id, String sessionId) throws UsuarioNotFoundException {
        boolean isAdmin = keycloakService.hasRoleAdmin(sessionId);
        return usuarioService.buscarUsuario(id, isAdmin, sessionId);}

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
        Perfil perfilSalvo = perfilService.salvar(perfil);
        Usuario solicitante = buscarUsuarioPorKcId(sessionId);
        List<Documento> documentos = armazenamentoService.salvarArquivo(arquivos);
        return solicitacaoPerfilService.solicitarPerfil(perfilSalvo, solicitante, documentos);
    }

    public SolicitacaoPerfil buscarSolicitacao(Long id, String sessionId) throws SolicitacaoNotFoundException {
        SolicitacaoPerfil solicitacao = solicitacaoPerfilService.buscarSolicitacao(id);
        if(!solicitacao.getSolicitante().getKcId().equals(sessionId) && !keycloakService.hasRoleAdmin(sessionId)){
            throw new GlobalAccessDeniedException("Você não tem permissão para acessar este recurso");
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

    public Resource baixarDocumentosSolicitacao(Long id, String sessionId) throws SolicitacaoNotFoundException, IOException {
        SolicitacaoPerfil solicitacao = solicitacaoPerfilService.buscarSolicitacao(id);
        if(!solicitacao.getSolicitante().getKcId().equals(sessionId) && !keycloakService.hasRoleAdmin(sessionId)){
            throw new GlobalAccessDeniedException("Você não tem permissão para acessar este recurso");
        }
        return armazenamentoService.carregarArquivoZip(solicitacao.getDocumentos());
    }

    @Transactional
    public SolicitacaoPerfil aceitarSolicitacao(Long id, SolicitacaoPerfil parecer, String sessionId) throws SolicitacaoNotFoundException, UsuarioNotFoundException {
        Usuario usuario = buscarUsuarioPorKcId(sessionId);
        parecer.setResponsavel(usuario);
        SolicitacaoPerfil solicitacaoPerfil =  solicitacaoPerfilService.aceitarSolicitacao(id, parecer);
        String tipoPerfil = solicitacaoPerfil.getPerfil().getClass().getSimpleName().toLowerCase();
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



    // ================== Unidade Administrativa ================== //
    public UnidadeAdministrativa salvar(UnidadeAdministrativa unidadeAdministrativa, Long paiId) throws UnidadeAdministrativaNotFoundException {
        return unidadeAdministrativaService.salvar(unidadeAdministrativa, paiId);
    }
    public UnidadeAdministrativa buscarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException{
        return unidadeAdministrativaService.buscarUnidadeAdministrativa(id);
    }
    public List<UnidadeAdministrativa> listarUnidadesAdministrativas() {
        return unidadeAdministrativaService.listarUnidadesAdministrativas();
    }

    public List<UnidadeAdministrativa> montarArvore() {
        return unidadeAdministrativaService.montarArvore();
    }

    public List<UnidadeAdministrativa> listarUnidadesFilhas(Long id) {
        return unidadeAdministrativaService.listarUnidadesFilhas(id);
    }

    public void deletarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException{
        unidadeAdministrativaService.deletarUnidadeAdministrativa(id);
    }

    public UnidadeAdministrativa editarUnidadeAdministrativa(Long id, UnidadeAdministrativa novaUnidadeAdministrativa) throws UnidadeAdministrativaNotFoundException {
        return unidadeAdministrativaService.editarUnidadeAdministrativa(novaUnidadeAdministrativa, id);
    }


    // ==================Tipo Unidade Administrativa ================== //
     public TipoUnidadeAdministrativa salvarTipo(TipoUnidadeAdministrativa tipoUnidadeAdministrativa) throws TipoUnidadeAdministrativaDuplicadoException {
        return tipoUnidadeAdministrativaService.salvar(tipoUnidadeAdministrativa);
    }

    public TipoUnidadeAdministrativa buscarTipo(Long id) throws TipoUnidadeAdministrativaNotFoundException {
        return tipoUnidadeAdministrativaService.buscar(id);
    }
    public List<TipoUnidadeAdministrativa> listarTipos() {
       return tipoUnidadeAdministrativaService.listar();

    }
    public TipoUnidadeAdministrativa editarTipo(Long id, TipoUnidadeAdministrativa novoTipo) throws TipoUnidadeAdministrativaNotFoundException, TipoUnidadeAdministrativaDuplicadoException {
        return tipoUnidadeAdministrativaService.editar(id, novoTipo);
    }

    public void deletarTipo(Long id) throws TipoUnidadeAdministrativaNotFoundException {
        tipoUnidadeAdministrativaService.deletar(id);
    }
}
