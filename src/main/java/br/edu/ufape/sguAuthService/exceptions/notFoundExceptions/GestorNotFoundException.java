package br.edu.ufape.sguAuthService.exceptions.notFoundExceptions;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND, reason = "Gestor não encontrado, verifique se o usuário é um gestor")
public class GestorNotFoundException extends ChangeSetPersister.NotFoundException {
}
