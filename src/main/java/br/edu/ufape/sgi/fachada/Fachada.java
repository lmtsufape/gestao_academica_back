package br.edu.ufape.sgi.fachada;


import br.edu.ufape.sgi.exceptions.aluno.AlunoNotFoundException;
import br.edu.ufape.sgi.exceptions.unidadeAdministrativa.UnidadeAdministrativaNotFoundException;
import br.edu.ufape.sgi.models.Aluno;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import br.edu.ufape.sgi.servicos.interfaces.AlunoService;
import br.edu.ufape.sgi.servicos.interfaces.UnidadeAdministrativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component @RequiredArgsConstructor

public class Fachada {
    private final AlunoService alunoService;
    private final UnidadeAdministrativaService unidadeAdministrativaService;

    // ================== Aluno ================== //
    public Aluno salvar(Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    public Aluno buscarAluno(Long id) throws AlunoNotFoundException { return alunoService.buscarAluno(id);
    }
    public List<Aluno> listarAlunos() {return alunoService.listarAlunos();}

    public void deletarAluno(Long id) throws AlunoNotFoundException {alunoService.deletarAluno(id);}


    // ================== Unidade Administrativa ================== //
    public UnidadeAdministrativa salvar(UnidadeAdministrativa unidadeAdministrativa) {
        return unidadeAdministrativaService.salvar(unidadeAdministrativa);
    }

    public UnidadeAdministrativa buscarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException{
        return unidadeAdministrativaService.buscarUnidadeAdministrativa(id);
    }
    public List<UnidadeAdministrativa> listarUnidadesAdministrativas() {
        return unidadeAdministrativaService.listarUnidadesAdministrativas();
    }

    public void deletarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException{
        unidadeAdministrativaService.deletarUnidadeAdministrativa(id);
    }

}
