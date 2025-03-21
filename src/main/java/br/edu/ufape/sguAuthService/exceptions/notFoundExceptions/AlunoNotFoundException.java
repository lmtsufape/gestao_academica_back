package br.edu.ufape.sguAuthService.exceptions.notFoundExceptions;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND, reason = "Aluno não encontrado, verifique se o usuário é um aluno")
public class AlunoNotFoundException extends ChangeSetPersister.NotFoundException {
}
