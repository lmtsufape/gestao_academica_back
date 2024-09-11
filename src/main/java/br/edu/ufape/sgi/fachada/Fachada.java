package br.edu.ufape.sgi.fachada;

import br.edu.ufape.sgi.models.Aluno;
import br.edu.ufape.sgi.servicos.interfaces.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor

public class Fachada {
    private final AlunoService alunoService;

    public Aluno salvar(Aluno aluno) {
        return alunoService.salvar(aluno);
    }
}
