package br.edu.ufape.sgi.servicos;

import br.edu.ufape.sgi.dados.AlunoRepository;
import br.edu.ufape.sgi.models.Aluno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor

public class AlunoService implements br.edu.ufape.sgi.servicos.interfaces.AlunoService {
    private final AlunoRepository alunoRepository;

    @Override
    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
}
