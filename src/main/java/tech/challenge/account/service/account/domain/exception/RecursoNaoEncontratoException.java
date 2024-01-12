package tech.challenge.account.service.account.domain.exception;

public class RecursoNaoEncontratoException extends RuntimeException {
    public RecursoNaoEncontratoException(String message) {
        super(message);
    }
}