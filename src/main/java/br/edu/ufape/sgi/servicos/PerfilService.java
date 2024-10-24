package br.edu.ufape.sgi.servicos;


import br.edu.ufape.sgi.dados.PerfilRepository;
import br.edu.ufape.sgi.exceptions.ExceptionUtil;
import br.edu.ufape.sgi.models.Perfil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilService implements br.edu.ufape.sgi.servicos.interfaces.PerfilService {
    private final PerfilRepository perfilRepository;

    @Override
    public Perfil salvar(Perfil perfil) {
        try {
            return perfilRepository.save(perfil);
        } catch (DataIntegrityViolationException e) {
            ExceptionUtil.handleDataIntegrityViolationException(e);
        }
        return null;
    }

    @Override
    public void deletarPerfil(Long id) {
        perfilRepository.deleteById(id);
    }
}
