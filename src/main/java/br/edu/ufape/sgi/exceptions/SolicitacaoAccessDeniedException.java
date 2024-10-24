package br.edu.ufape.sgi.exceptions;

import org.springframework.security.access.AccessDeniedException;

public class SolicitacaoAccessDeniedException extends AccessDeniedException {
    public SolicitacaoAccessDeniedException(String msg) {
        super(msg);
    }
}
