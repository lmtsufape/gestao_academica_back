package br.edu.ufape.sgi.exceptions.usuario;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND, reason = "Usuário não encontrado")
public class UsuarioNotFoundException extends ChangeSetPersister.NotFoundException {
}
