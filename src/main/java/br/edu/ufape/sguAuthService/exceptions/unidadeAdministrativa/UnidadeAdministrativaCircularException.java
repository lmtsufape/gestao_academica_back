package br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Uma Unidade Administrativa não pode ser sua própria unidade pai.")
public class UnidadeAdministrativaCircularException extends RuntimeException {
    public UnidadeAdministrativaCircularException() {
        super("Uma Unidade Administrativa não pode ser sua própria unidade pai.");
    }
}
