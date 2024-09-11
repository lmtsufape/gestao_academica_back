package br.edu.ufape.sgi.exceptions;

public class UniqueConstraintViolationException extends RuntimeException {
    private final String field;

    public UniqueConstraintViolationException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}