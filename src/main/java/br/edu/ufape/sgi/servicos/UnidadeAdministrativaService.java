package br.edu.ufape.sgi.servicos;

import br.edu.ufape.sgi.dados.TipoUnidadeAdministrativaRepository;
import br.edu.ufape.sgi.dados.UnidadeAdministrativaRepository;
import br.edu.ufape.sgi.exceptions.ExceptionUtil;
import br.edu.ufape.sgi.exceptions.unidadeAdministrativa.UnidadeAdministrativaNotFoundException;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UnidadeAdministrativaService implements br.edu.ufape.sgi.servicos.interfaces.UnidadeAdministrativaService {
    private final UnidadeAdministrativaRepository unidadeAdministrativaRepository;
    private final TipoUnidadeAdministrativaRepository tipoUnidadeAdministrativaRepository;

    @Override
    public UnidadeAdministrativa salvar(UnidadeAdministrativa unidadeAdministrativa,Long tipoId) throws UnidadeAdministrativaNotFoundException {
        TipoUnidadeAdministrativa tipoUnidadeAdministrativa = tipoUnidadeAdministrativaRepository
        .findById(tipoId)
        .orElseThrow(UnidadeAdministrativaNotFoundException::new);
        unidadeAdministrativa.setTipoUnidadeAdministrativa(tipoUnidadeAdministrativa);
        return unidadeAdministrativaRepository.save(unidadeAdministrativa);
    }

    @Override
    public UnidadeAdministrativa buscarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException {
        return unidadeAdministrativaRepository.findById(id).orElseThrow(UnidadeAdministrativaNotFoundException::new);
    }

    @Override
    public List<UnidadeAdministrativa> listarUnidadesAdministrativas() {
        return unidadeAdministrativaRepository.findAll();
    }

    @Override
    public void deletarUnidadeAdministrativa(Long id) throws UnidadeAdministrativaNotFoundException {
        if (!unidadeAdministrativaRepository.existsById(id)) throw new UnidadeAdministrativaNotFoundException();
        unidadeAdministrativaRepository.deleteById(id);
    }
}
