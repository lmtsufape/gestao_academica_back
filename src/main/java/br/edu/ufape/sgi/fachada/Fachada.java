package br.edu.ufape.sgi.fachada;


import br.edu.ufape.sgi.exceptions.aluno.AlunoNotFoundException;
import br.edu.ufape.sgi.models.Aluno;
import br.edu.ufape.sgi.servicos.interfaces.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @RequiredArgsConstructor

public class Fachada {
    private final AlunoService alunoService;

    // ================== Aluno ================== //
    public Aluno salvar(Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    public Aluno buscarAluno(Long id) throws AlunoNotFoundException { return alunoService.buscarAluno(id);
    }
    public List<Aluno> listarAlunos() {return alunoService.listarAlunos();}

    public void deletarAluno(Long id) throws AlunoNotFoundException {alunoService.deletarAluno(id);}

}
