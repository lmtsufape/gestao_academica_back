package br.edu.ufape.sguAuthService.exceptions.auth;

public class KeycloakAuthenticationException extends RuntimeException {
    public KeycloakAuthenticationException(String message) {
        super(message);
    }

    public KeycloakAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
