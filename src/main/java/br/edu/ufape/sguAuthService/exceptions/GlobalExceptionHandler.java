package br.edu.ufape.sguAuthService.exceptions;

import br.edu.ufape.sguAuthService.comunicacao.dto.erros.ErrorResponse;
import br.edu.ufape.sguAuthService.exceptions.auth.KeycloakAuthenticationException;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UniqueConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleUniqueConstraintViolation(UniqueConstraintViolationException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("field", ex.getField());
        response.put("message", ex.getMessage());
        return response;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Map<String, Map<String, String>> ret = new HashMap<>();
        ret.put("errors", errors);
        return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(KeycloakAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleKeycloakAuthenticationException(KeycloakAuthenticationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}