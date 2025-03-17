package br.edu.ufape.sguAuthService.servicos;


import br.edu.ufape.sguAuthService.dados.CursoRepository;
import br.edu.ufape.sguAuthService.exceptions.CursoDuplicadoException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.CursoNotFoundException;
import br.edu.ufape.sguAuthService.models.Curso;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CursoService implements br.edu.ufape.sguAuthService.servicos.interfaces.CursoService {
    private final CursoRepository cursoRepository;
    private final ModelMapper modelMapper;

    @Override
    public Curso salvar(Curso curso) throws CursoDuplicadoException {
        cursoRepository.findByNomeEqualsIgnoreCase(curso.getNome())
                .ifPresent(_ -> { throw new CursoDuplicadoException(); });
        return cursoRepository.save(curso);
    }

    @Override
    public Curso buscar(Long id) throws CursoNotFoundException {
        return cursoRepository.findById(id).orElseThrow(CursoNotFoundException::new);
    }

    @Override
    public List<Curso> listar() {
        return cursoRepository.findByAtivoTrue();
    }

    @Override
    public Curso editar(Long id, Curso novoCurso) throws CursoNotFoundException, CursoDuplicadoException {
        cursoRepository.findByNomeEqualsIgnoreCase(novoCurso.getNome())
                .ifPresent(_ -> { throw new CursoDuplicadoException(); });
        Curso antigoCurso = cursoRepository.findById(id).orElseThrow(CursoNotFoundException::new);
        modelMapper.map(novoCurso, antigoCurso);
        return cursoRepository.save(antigoCurso);
    }

    @Override
    public void deletar(Long id) throws CursoNotFoundException {
        Curso curso = cursoRepository.findById(id).orElseThrow(CursoNotFoundException::new);
        curso.setAtivo(false);
        cursoRepository.save(curso);
    }


}
