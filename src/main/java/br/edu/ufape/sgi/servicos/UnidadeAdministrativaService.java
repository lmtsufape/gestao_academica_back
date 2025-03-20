package br.edu.ufape.sgi.servicos;

import java.util.List;

import br.edu.ufape.sgi.exceptions.notFoundExceptions.TipoUnidadeAdministrativaNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import br.edu.ufape.sgi.dados.TipoUnidadeAdministrativaRepository;
import br.edu.ufape.sgi.dados.UnidadeAdministrativaRepository;
import br.edu.ufape.sgi.exceptions.unidadeAdministrativa.*;
import br.edu.ufape.sgi.models.TipoUnidadeAdministrativa;
import br.edu.ufape.sgi.models.UnidadeAdministrativa;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnidadeAdministrativaService implements br.edu.ufape.sgi.servicos.interfaces.UnidadeAdministrativaService {
    private final UnidadeAdministrativaRepository unidadeAdministrativaRepository;
    private final TipoUnidadeAdministrativaRepository tipoUnidadeAdministrativaRepository;
    private final ModelMapper modelMapper;

    @Override
    public UnidadeAdministrativa salvar(UnidadeAdministrativa unidadeAdministrativa, Long paiId) {
        if (unidadeAdministrativaRepository.existsByCodigo(unidadeAdministrativa.getCodigo())) {
            throw new UnidadeAdministrativaDuplicadaException("Código da unidade administrativa já está em uso.");
        }

        if (paiId != null && unidadeAdministrativa.getId() != null && unidadeAdministrativa.getId().equals(paiId)) {
            throw new UnidadeAdministrativaCircularException();
        }

        TipoUnidadeAdministrativa tipoUnidadeAdministrativa = tipoUnidadeAdministrativaRepository
                .findById(unidadeAdministrativa.getTipoUnidadeAdministrativa().getId())
                .orElseThrow(() -> new TipoUnidadeAdministrativaNotFoundException("Tipo de Unidade Administrativa não encontrado."));

        unidadeAdministrativa.setTipoUnidadeAdministrativa(tipoUnidadeAdministrativa);

        if (paiId != null) {
            UnidadeAdministrativa parent = unidadeAdministrativaRepository.findById(paiId)
                    .orElseThrow(UnidadeAdministrativaNotFoundException::new);
            unidadeAdministrativa.setUnidadePai(parent);
        }

        return unidadeAdministrativaRepository.save(unidadeAdministrativa);
    }

    @Override
    public UnidadeAdministrativa editarUnidadeAdministrativa(UnidadeAdministrativa novaUnidadeAdministrativa, Long id) {
        UnidadeAdministrativa unidadeAdministrativaAtual = unidadeAdministrativaRepository.findById(id)
                .orElseThrow(UnidadeAdministrativaNotFoundException::new);

        if (novaUnidadeAdministrativa.getUnidadePai() != null
                && novaUnidadeAdministrativa.getUnidadePai().getId().equals(id)) {
            throw new UnidadeAdministrativaCircularException();
        }

        modelMapper.map(novaUnidadeAdministrativa, unidadeAdministrativaAtual);
        return unidadeAdministrativaRepository.save(unidadeAdministrativaAtual);
    }

    @Override
    public UnidadeAdministrativa buscarUnidadeAdministrativa(Long id) {
        return unidadeAdministrativaRepository.findById(id)
                .orElseThrow(UnidadeAdministrativaNotFoundException::new);
    }

    @Override
    public List<UnidadeAdministrativa> listarUnidadesAdministrativas() {
        return unidadeAdministrativaRepository.findAll();
    }

    @Override
    public List<UnidadeAdministrativa> montarArvore() {
        return unidadeAdministrativaRepository.findByUnidadePaiIsNull();
    }

    @Override
    public List<UnidadeAdministrativa> listarUnidadesFilhas(Long id) {
        return unidadeAdministrativaRepository.findByUnidadePaiId(id);
    }

    @Override
    public void deletarUnidadeAdministrativa(Long id) {
        UnidadeAdministrativa unidade = unidadeAdministrativaRepository.findById(id)
                .orElseThrow(UnidadeAdministrativaNotFoundException::new);

        if (!unidade.getUnidadesFilhas().isEmpty()) {
            throw new UnidadeAdministrativaComDependenciasException("Não é possível excluir a unidade, pois ela possui unidades filhas.");
        }

        unidadeAdministrativaRepository.deleteById(id);
    }
}
