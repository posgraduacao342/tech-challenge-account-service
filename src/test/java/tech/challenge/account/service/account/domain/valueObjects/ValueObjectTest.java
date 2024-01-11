package tech.challenge.account.service.account.domain.valueObjects;

import org.junit.jupiter.api.Test;
import tech.challenge.account.service.account.domain.exception.AtributoInvalidoException;

import static org.junit.jupiter.api.Assertions.*;

class ValueObjectTest {

    private static class TestarValueObject extends ValueObject<String> {

        public TestarValueObject(String value) {
            super(value);
        }

        @Override
        protected void validar(String value) {
            // Lógica de validação específica para o teste
            if (value == null || value.isEmpty()) {
                throw new AtributoInvalidoException("Valor não pode ser nulo ou vazio.");
            }
        }
    }

    @Test
    void deveCriarUmValueObject() {
        // Act
        TestarValueObject testValueObject = new TestarValueObject("Teste");
        // Asserts
        assertEquals("Teste", testValueObject.getValue());
    }

    @Test
    void deveLancarExceçãoAoCriarUmValueObjectInvalido() {
        // Act / Asserts
        assertThrows(AtributoInvalidoException.class, () -> new TestarValueObject(null));
    }

    @Test
    void deveValidarAIgualdadeEHashcode() {
        // Arrange
        var obj1 = new TestarValueObject("Valor1");
        var obj2 = new TestarValueObject("Valor1");
        var obj3 = new TestarValueObject("Valor2");

        // Act / Asserts
        assertEquals(obj1, obj2);
        assertNotEquals(obj1, obj3);
        assertEquals(obj1.hashCode(), obj2.hashCode());
        assertNotEquals(obj1.hashCode(), obj3.hashCode());
    }
}

