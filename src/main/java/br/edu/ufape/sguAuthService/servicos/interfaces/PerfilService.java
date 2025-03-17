package br.edu.ufape.sguAuthService.servicos.interfaces;

import br.edu.ufape.sguAuthService.models.Perfil;

public interface PerfilService {
    Perfil salvar(Perfil perfil);

    void deletarPerfil(Long id);
}
