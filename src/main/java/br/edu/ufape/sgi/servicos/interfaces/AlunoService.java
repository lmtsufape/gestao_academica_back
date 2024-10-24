package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.exceptions.notFoundExceptions.AlunoNotFoundException;
import br.edu.ufape.sgi.models.Aluno;

import java.util.List;

public interface AlunoService {

    Aluno buscarAluno(Long id) throws AlunoNotFoundException;

    List<Aluno> listarAlunos();

    void deletarAluno(Long id) throws AlunoNotFoundException;
}
