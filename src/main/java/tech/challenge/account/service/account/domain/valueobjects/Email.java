package tech.challenge.account.service.account.domain.valueobjects;

import  tech.challenge.account.service.account.domain.exception.AtributoInvalidoException;

public class Email extends ValueObject<String> {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";

    public Email(String value) {
        super(value);
        validar(value);
    }

    @Override
    protected void validar(String email) {
        if (!isValidEmail(email)) {
            throw new AtributoInvalidoException("Email inválido!");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }
}