package tech.challenge.account.service.account.domain.valueobjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import tech.challenge.account.service.account.domain.exception.AtributoInvalidoException;

class EmailTest {
    @Test
    void testValidEmail() {
        // Arrange
        var emailValido = "usuario@example.com";

        //Act
        var email = new Email(emailValido);

        //Asserts
        assertEquals(emailValido, email.getValue());
    }

    @Test
    void testInvalidEmailFormat() {
        // Arrange
        var emailComFormatoInvalido = "usuario@com";

        // Act / Asserts
        Exception exception = assertThrows(AtributoInvalidoException.class, () -> new Email(emailComFormatoInvalido));
        assertEquals("Email inválido!", exception.getMessage());
    }

    @Test
    void testInvalidEmailCharacters() {
        // Arrange
        var emailComCaracteresInvalidos = "usuario@exa!mple.com\"";

        // Act / Asserts
        Exception exception = assertThrows(AtributoInvalidoException.class, () -> new Email(emailComCaracteresInvalidos));
        assertEquals("Email inválido!", exception.getMessage());
    }

    @Test
    void testEqualsAndHashCode() {
        Email email1 = new Email("usuario@example.com");
        Email email2 = new Email("usuario@example.com");
        Email email3 = new Email("outro@example.com");

        assertEquals(email1, email2);
        assertNotEquals(email1, email3);
        assertEquals(email1.hashCode(), email2.hashCode());
        assertNotEquals(email1.hashCode(), email3.hashCode());
    }
}
