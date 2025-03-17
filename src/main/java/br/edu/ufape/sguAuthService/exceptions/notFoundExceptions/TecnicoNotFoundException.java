package br.edu.ufape.sguAuthService.exceptions.notFoundExceptions;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND, reason = "Tecnico não encontrado, verifique se o usuário é um tecnico")
public class TecnicoNotFoundException extends ChangeSetPersister.NotFoundException {
}
