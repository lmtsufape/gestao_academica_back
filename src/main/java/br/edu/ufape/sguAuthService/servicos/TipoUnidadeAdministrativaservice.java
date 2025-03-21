package br.edu.ufape.sguAuthService.servicos;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.edu.ufape.sguAuthService.dados.TipoUnidadeAdministrativaRepository;
import br.edu.ufape.sguAuthService.exceptions.TipoUnidadeAdministrativaDuplicadoException;
import br.edu.ufape.sguAuthService.exceptions.notFoundExceptions.TipoUnidadeAdministrativaNotFoundException;
import br.edu.ufape.sguAuthService.models.TipoUnidadeAdministrativa;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoUnidadeAdministrativaservice implements br.edu.ufape.sguAuthService.servicos.interfaces.TipoUnidadeAdministrativaService {
    private final TipoUnidadeAdministrativaRepository tipoUnidadeAdministrativaRepository;
    private final ModelMapper modelMapper;

    @Override
    public TipoUnidadeAdministrativa salvar(TipoUnidadeAdministrativa tipoUnidadeAdministrativa) throws TipoUnidadeAdministrativaDuplicadoException {
        tipoUnidadeAdministrativaRepository.findByNomeEqualsIgnoreCase(tipoUnidadeAdministrativa.getNome())
                .ifPresent(_ -> { throw new TipoUnidadeAdministrativaDuplicadoException("Tipo de unidade administrativa com o nome '" + tipoUnidadeAdministrativa.getNome() + "' já existe."); });
        return tipoUnidadeAdministrativaRepository.save(tipoUnidadeAdministrativa);
    }

    @Override
    public TipoUnidadeAdministrativa buscar(Long id) throws TipoUnidadeAdministrativaNotFoundException {
        return tipoUnidadeAdministrativaRepository.findById(id).orElseThrow(TipoUnidadeAdministrativaNotFoundException::new);
    }

    @Override
    public List<TipoUnidadeAdministrativa> listar() {
        return tipoUnidadeAdministrativaRepository.findAll();
    }

    @Override
    public TipoUnidadeAdministrativa editar(Long id, TipoUnidadeAdministrativa novoTipoUnidadeAdministrativa) throws TipoUnidadeAdministrativaNotFoundException, TipoUnidadeAdministrativaDuplicadoException {
        tipoUnidadeAdministrativaRepository.findByNomeEqualsIgnoreCase(novoTipoUnidadeAdministrativa.getNome())
                .ifPresent(existing -> { throw new TipoUnidadeAdministrativaDuplicadoException("Tipo de unidade administrativa com o nome '" + existing.getNome() + "' já existe."); });
        TipoUnidadeAdministrativa antigoTipoUnidadeAdministrativa = tipoUnidadeAdministrativaRepository.findById(id).orElseThrow(TipoUnidadeAdministrativaNotFoundException::new);
        modelMapper.map(novoTipoUnidadeAdministrativa, antigoTipoUnidadeAdministrativa);
        return tipoUnidadeAdministrativaRepository.save(antigoTipoUnidadeAdministrativa);
    }

    @Override
    public void deletar(Long id) throws TipoUnidadeAdministrativaNotFoundException {
        TipoUnidadeAdministrativa tipoUnidadeAdministrativa = tipoUnidadeAdministrativaRepository.findById(id).orElseThrow(TipoUnidadeAdministrativaNotFoundException::new);
        tipoUnidadeAdministrativaRepository.delete(tipoUnidadeAdministrativa);
    }
}
