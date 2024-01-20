package tech.challenge.account.service.account.domain.valueObjects;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import tech.challenge.account.service.account.domain.exception.AtributoInvalidoException;

class CPFTest {

    @Test
    void deveGerarUmCPFValido() {
        // Arrange
        var cpfValido = "123.456.789-09";

        //Act
        var cpf = new CPF(cpfValido);

        //Asserts
        assertEquals(cpfValido, cpf.getValue());
    }

    @Test
    void deveGerarOErroDeFormatoInvalido() {
        // Arrange
        var cpfComFormatoInvalido = "123.45X.789-09";

        // Act / Asserts
        Exception exception = assertThrows(AtributoInvalidoException.class, () -> new CPF(cpfComFormatoInvalido));
        assertEquals("O CPF deve conter apenas números, pontos e traços, e ter 11 dígitos.", exception.getMessage());
    }

    @Test
    void deveGerarOErroDeComprimento() {
        // Arrange
        var cpfComComprimentoInvalido = "123.456.789-012";

        // Act / Asserts
        Exception exception = assertThrows(AtributoInvalidoException.class, () -> new CPF(cpfComComprimentoInvalido));
        assertEquals("O CPF deve conter apenas números, pontos e traços, e ter 11 dígitos.", exception.getMessage());
    }

    @Test
    void testGerarOErroDeCPFInvalido() {
        // Arrange
        var cpfInvalido = "111.222.333-44";

        // Act / Asserts
        Exception exception = assertThrows(AtributoInvalidoException.class, () -> new CPF(cpfInvalido));
        assertEquals("CPF inválido.", exception.getMessage());
    }
}
