package br.edu.ufape.sguAuthService.servicos;

import br.edu.ufape.sguAuthService.dados.TipoUnidadeAdministrativaRepository;
import br.edu.ufape.sguAuthService.dados.UnidadeAdministrativaRepository;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.TipoUnidadeAdministrativaNotFoundException;
import br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa.UnidadeAdministrativaCircularException;
import br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa.UnidadeAdministrativaComDependenciasException;
import br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa.UnidadeAdministrativaDuplicadaException;
import br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa.UnidadeAdministrativaNotFoundException;
import br.edu.ufape.sguAuthService.models.TipoUnidadeAdministrativa;
import br.edu.ufape.sguAuthService.models.UnidadeAdministrativa;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UnidadeAdministrativaService implements br.edu.ufape.sguAuthService.servicos.interfaces.UnidadeAdministrativaService {
    private final UnidadeAdministrativaRepository unidadeAdministrativaRepository;
    private final TipoUnidadeAdministrativaRepository tipoUnidadeAdministrativaRepository;
    private final ModelMapper modelMapper;

    @Override
    public UnidadeAdministrativa salvar(UnidadeAdministrativa unidadeAdministrativa, Long paiId) {
        if (unidadeAdministrativaRepository.existsByCodigo(unidadeAdministrativa.getCodigo())) {
            throw new UnidadeAdministrativaDuplicadaException("Código da unidade administrativa já está em uso.");
        }

        if (unidadeAdministrativa.getId() != null && unidadeAdministrativa.getId().equals(paiId)) {
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
