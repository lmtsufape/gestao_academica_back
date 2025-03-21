package br.edu.ufape.sguAuthService.exceptions.unidadeAdministrativa;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Não é possível excluir a unidade administrativa, pois ela possui dependências.")
public class UnidadeAdministrativaComDependenciasException extends RuntimeException {
    public UnidadeAdministrativaComDependenciasException(String message) {
        super(message);
    }
}
