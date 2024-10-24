package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.models.Perfil;

public interface PerfilService {
    Perfil salvar(Perfil perfil);

    void deletarPerfil(Long id);
}
