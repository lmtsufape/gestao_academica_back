package br.edu.ufape.sguAuthService.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Tipo de unidade administrativa já existe!")
public class TipoUnidadeAdministrativaDuplicadoException extends RuntimeException {
    public TipoUnidadeAdministrativaDuplicadoException(String message) {
        super(message);
    }
}
