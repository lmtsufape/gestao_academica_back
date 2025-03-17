package br.edu.ufape.sguAuthService.servicos;

import br.edu.ufape.sguAuthService.dados.UnidadeAdministrativaRepository;
import br.edu.ufape.sguAuthService.exceptions.ExceptionUtil;
import br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa.UnidadeAdministrativaNotFoundException;
import br.edu.ufape.sguAuthService.models.UnidadeAdministrativa;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UnidadeAdministrativaService implements br.edu.ufape.sguAuthService.servicos.interfaces.UnidadeAdministrativaService {
    private final UnidadeAdministrativaRepository unidadeAdministrativaRepository;

    @Override
    public UnidadeAdministrativa salvar(UnidadeAdministrativa unidadeAdministrativa) {
        try {
            return unidadeAdministrativaRepository.save(unidadeAdministrativa);
        }catch (DataIntegrityViolationException e){
            ExceptionUtil.handleDataIntegrityViolationException(e);
            return null;
        }
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
