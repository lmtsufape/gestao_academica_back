package br.edu.ufape.sgi.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ufape.sgi.dados.TipoUnidadeAdministrativaRepository;
import br.edu.ufape.sgi.dados.UnidadeAdministrativaRepository;
import br.edu.ufape.sgi.exceptions.unidadeAdministrativa.UnidadeAdministrativaNotFoundException;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UnidadeAdministrativaService implements br.edu.ufape.sgi.servicos.interfaces.UnidadeAdministrativaService {
    private final UnidadeAdministrativaRepository unidadeAdministrativaRepository;
    private final TipoUnidadeAdministrativaRepository tipoUnidadeAdministrativaRepository;

    public UnidadeAdministrativa salvar(UnidadeAdministrativa unidadeAdministrativa, Optional<Long> paiId) throws UnidadeAdministrativaNotFoundException {
        TipoUnidadeAdministrativa tipoUnidadeAdministrativa = tipoUnidadeAdministrativaRepository
            .findById(unidadeAdministrativa.getTipoUnidadeAdministrativa().getId())
            .orElseThrow(UnidadeAdministrativaNotFoundException::new);
        unidadeAdministrativa.setTipoUnidadeAdministrativa(tipoUnidadeAdministrativa);
    
        if (paiId != null) {
            UnidadeAdministrativa parent = unidadeAdministrativaRepository.findById(unidadeAdministrativa.getUnidadePai().getId())
                .orElseThrow(UnidadeAdministrativaNotFoundException::new);
            unidadeAdministrativa.setUnidadePai(parent);
        }
    
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
