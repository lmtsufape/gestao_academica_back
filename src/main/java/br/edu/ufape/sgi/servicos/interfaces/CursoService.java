package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.exceptions.CursoDuplicadoException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.CursoNotFoundException;
import br.edu.ufape.sgi.models.Curso;

import java.util.List;

public interface CursoService {
    Curso salvar(Curso curso) throws CursoDuplicadoException;

    Curso buscar(Long id) throws CursoNotFoundException;

    List<Curso> listar();

    Curso editar(Long id, Curso novoCurso) throws CursoNotFoundException;

    void deletar(Long id) throws CursoNotFoundException;
}
