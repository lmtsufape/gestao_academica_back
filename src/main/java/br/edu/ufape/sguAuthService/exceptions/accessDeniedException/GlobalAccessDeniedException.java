package br.edu.ufape.sguAuthService.exceptions.accessDeniedException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.FORBIDDEN, reason = "Você não tem permissão para acessar este recurso")
public class GlobalAccessDeniedException extends AccessDeniedException {
    public GlobalAccessDeniedException(String msg) {
        super(msg);
    }
}
