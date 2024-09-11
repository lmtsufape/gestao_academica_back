package br.edu.ufape.sgi.exceptions.aluno;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND, reason = "Aluno não encontrado")
public class AlunoNotFoundException extends ChangeSetPersister.NotFoundException {
}
