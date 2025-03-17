package br.edu.ufape.sguAuthService.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.CONFLICT, reason = "Perfil já solicitado!")
public class SolicitacaoDuplicadaException extends RuntimeException {
}

