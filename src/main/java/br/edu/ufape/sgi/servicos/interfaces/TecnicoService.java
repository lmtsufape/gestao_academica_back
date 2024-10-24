package br.edu.ufape.sgi.servicos.interfaces;

import br.edu.ufape.sgi.exceptions.notFoundExceptions.TecnicoNotFoundException;
import br.edu.ufape.sgi.exceptions.notFoundExceptions.UsuarioNotFoundException;
import br.edu.ufape.sgi.models.Usuario;

import java.util.List;

public interface TecnicoService {
    List<Usuario> getTecnicos();

    Usuario buscarTecnico(Long id, boolean isAdm, String sessionId) throws TecnicoNotFoundException, UsuarioNotFoundException;
}
