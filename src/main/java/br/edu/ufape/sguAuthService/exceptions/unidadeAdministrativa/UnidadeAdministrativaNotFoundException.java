package br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND, reason = "Unidade Administrativa não encontrada")

public class UnidadeAdministrativaNotFoundException extends ChangeSetPersister.NotFoundException {
}
