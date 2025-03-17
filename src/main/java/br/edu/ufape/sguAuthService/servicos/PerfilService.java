package br.edu.ufape.sguAuthService.servicos;


import br.edu.ufape.sguAuthService.dados.PerfilRepository;
import br.edu.ufape.sguAuthService.exceptions.ExceptionUtil;
import br.edu.ufape.sguAuthService.models.Perfil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilService implements br.edu.ufape.sguAuthService.servicos.interfaces.PerfilService {
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
