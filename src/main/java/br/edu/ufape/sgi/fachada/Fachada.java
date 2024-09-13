package br.edu.ufape.sgi.fachada;


import br.edu.ufape.sgi.exceptions.aluno.AlunoNotFoundException;
import br.edu.ufape.sgi.exceptions.usuario.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.Aluno;
import br.edu.ufape.sgi.models.Usuario;
import br.edu.ufape.sgi.servicos.interfaces.AlunoService;
import br.edu.ufape.sgi.servicos.interfaces.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @RequiredArgsConstructor

public class Fachada {
    private final AlunoService alunoService;
    private final UsuarioService usuarioService;

    // ================== Aluno ================== //
    public Aluno salvar(Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    public Aluno buscarAluno(Long id) throws AlunoNotFoundException { return alunoService.buscarAluno(id);
    }
    public List<Aluno> listarAlunos() {return alunoService.listarAlunos();}

    public void deletarAluno(Long id) throws AlunoNotFoundException {alunoService.deletarAluno(id);}

    // ================== Visitante ================== //
    public Usuario salvar(Usuario usuario) {return usuarioService.salvar(usuario);}

    public Usuario buscarUsuario(Long id) throws UsuarioNotFoundException {return usuarioService.buscarUsuario(id);}

    public List<Usuario> listarUsuarios() {return usuarioService.listarUsuarios();}

    public void deletarUsuario(Long id) throws UsuarioNotFoundException {
        usuarioService.deletarUsuario(id);}
}
