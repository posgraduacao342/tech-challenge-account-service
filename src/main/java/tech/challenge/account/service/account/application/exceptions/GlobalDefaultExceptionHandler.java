package tech.challenge.account.service.account.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.challenge.account.service.account.domain.exception.RecursoJaExisteException;

@RestControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(RecursoJaExisteException.class)
    private ResponseEntity<ProblemDetail> handleResourceBadRequest(RecursoJaExisteException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }
}
