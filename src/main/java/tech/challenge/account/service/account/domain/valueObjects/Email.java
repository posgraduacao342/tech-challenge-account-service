package tech.challenge.account.service.account.domain.valueObjects;

import  tech.challenge.account.service.account.domain.exception.AtributoInvalidoException;
import lombok.Data;

import java.util.regex.Pattern;

@Data
public class Email {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    private final String value;

    public Email(String value) {
        if (!isValidEmail(value)) {
            throw new AtributoInvalidoException("Email inválido!");
        }
        this.value = value;
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return value.equals(email1.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}