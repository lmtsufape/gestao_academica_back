package br.edu.ufape.sgi.servicos;

import br.edu.ufape.sgi.dados.AlunoRepository;
import br.edu.ufape.sgi.exceptions.ExceptionUtil;
import br.edu.ufape.sgi.exceptions.aluno.AlunoNotFoundException;
import br.edu.ufape.sgi.models.Aluno;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor

public class AlunoService implements br.edu.ufape.sgi.servicos.interfaces.AlunoService {
    private final AlunoRepository alunoRepository;


    @Override
    public Aluno salvar(Aluno aluno) {
        try {
            return alunoRepository.save(aluno);
        }catch (DataIntegrityViolationException e){
            ExceptionUtil.handleDataIntegrityViolationException(e);
            return null;
        }
    }

    @Override
    public Aluno buscarAluno(Long id) throws AlunoNotFoundException {
        return alunoRepository.findById(id).orElseThrow(AlunoNotFoundException::new);
    }

    @Override
    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    @Override
    public void deletarAluno(Long id) throws AlunoNotFoundException {
        if (!alunoRepository.existsById(id)) throw new AlunoNotFoundException();
        alunoRepository.deleteById(id);
    }

}
