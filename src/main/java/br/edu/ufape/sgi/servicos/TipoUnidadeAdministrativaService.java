package br.edu.ufape.sgi.servicos;

import br.edu.ufape.sgi.dados.TipoUnidadeAdministrativaRepository;
import br.edu.ufape.sgi.exceptions.ExceptionUtil;
import br.edu.ufape.sgi.exceptions.tipoUnidadeAdministrativa.TipoUnidadeAdministrativaNotFoundException;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TipoUnidadeAdministrativaService  implements br.edu.ufape.sgi.servicos.interfaces.TipoUnidadeAdministrativaService {
    private final TipoUnidadeAdministrativaRepository tipoUnidadeAdministrativaRepository;

    @Override
    public TipoUnidadeAdministrativa salvar(TipoUnidadeAdministrativa tipoUnidadeAdministrativa) {
        try {
            return tipoUnidadeAdministrativaRepository.save(tipoUnidadeAdministrativa);
        } catch (DataIntegrityViolationException e) {
            ExceptionUtil.handleDataIntegrityViolationException(e);
            return null;
        }
    }

    @Override
    public TipoUnidadeAdministrativa buscarTipoUnidadeAdministrativa(Long id) throws TipoUnidadeAdministrativaNotFoundException {
        return tipoUnidadeAdministrativaRepository.findById(id).orElseThrow(TipoUnidadeAdministrativaNotFoundException::new);
    }

    @Override
    public List<TipoUnidadeAdministrativa> listarTiposUnidadeAdministrativa() {
        return tipoUnidadeAdministrativaRepository.findAll();
    }

    @Override
    public void deletarTipoUnidadeAdministrativa(Long id) throws TipoUnidadeAdministrativaNotFoundException {
        if (!tipoUnidadeAdministrativaRepository.existsById(id)) throw new TipoUnidadeAdministrativaNotFoundException();
        tipoUnidadeAdministrativaRepository.deleteById(id);
    }
}
