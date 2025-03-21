package br.edu.ufape.sguAuthService.exceptions.notFoundExceptions;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND, reason = "Unidade Administrativa não encontrada")
public class TipoUnidadeAdministrativaNotFoundException extends RuntimeException {
    public TipoUnidadeAdministrativaNotFoundException() {
        super("Tipo de Unidade Administrativa não encontrado");
    }
    public TipoUnidadeAdministrativaNotFoundException(String message) {
        super(message);
    }

}
