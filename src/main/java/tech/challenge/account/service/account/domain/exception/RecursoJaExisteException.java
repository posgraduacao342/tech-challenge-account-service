package tech.challenge.account.service.account.domain.exception;

public class RecursoJaExisteException extends RuntimeException {
    public RecursoJaExisteException(String message) {
        super(message);
    }
}
