package br.edu.ufape.sguAuthService.servicos.interfaces;

import br.edu.ufape.sguAuthService.exceptions.CursoDuplicadoException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.CursoNotFoundException;
import br.edu.ufape.sguAuthService.models.Curso;

import java.util.List;

public interface CursoService {
    Curso salvar(Curso curso) throws CursoDuplicadoException;

    Curso buscar(Long id) throws CursoNotFoundException;

    List<Curso> listar();

    Curso editar(Long id, Curso novoCurso) throws CursoNotFoundException;

    void deletar(Long id) throws CursoNotFoundException;
}
