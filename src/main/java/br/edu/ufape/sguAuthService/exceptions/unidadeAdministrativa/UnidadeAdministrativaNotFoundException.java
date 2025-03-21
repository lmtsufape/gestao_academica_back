package br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Unidade Administrativa não encontrada")
public class UnidadeAdministrativaNotFoundException extends RuntimeException {
    public UnidadeAdministrativaNotFoundException() {
        super("Unidade Administrativa não encontrada");
    }

    public UnidadeAdministrativaNotFoundException(String message) {
        super(message);
    }
}
