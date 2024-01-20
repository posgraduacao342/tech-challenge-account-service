package tech.challenge.account.service.account.domain.useCases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.challenge.account.service.account.domain.dto.CadastrarClienteDto;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.exception.RecursoJaExisteException;
import tech.challenge.account.service.account.domain.exception.RecursoNaoEncontratoException;
import tech.challenge.account.service.account.domain.ports.out.ClienteGatewayPort;
import tech.challenge.account.service.account.domain.valueObjects.CPF;
import tech.challenge.account.service.account.domain.valueObjects.Email;
import tech.challenge.account.service.account.helpers.ClienteHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CadastrarClienteUseCaseTest {
    @Mock
    ClienteGatewayPort clienteGatewayPort;

    CadastrarClienteUseCase useCase;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new CadastrarClienteUseCase(clienteGatewayPort);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveCadastrarUmCliente() {
        // Arrange
        var cliente = ClienteHelper.gerarCliente();
        cliente.setId(null);
        var dto = new CadastrarClienteDto(cliente.getEmail().getValue(),cliente.getCpf().getValue(), cliente.getNome());
        when(clienteGatewayPort.cadastrarCliente(cliente)).thenReturn(cliente);

        // Act
        var response = useCase.execute(dto);

        // Assert
        assertEquals(cliente.getNome(), response.getNome());
    }

    @Test
    void deveLancarException_QuandoRecursoJaExiste() {
        // Arrange
        var cliente = ClienteHelper.gerarCliente();
        cliente.setId(null);
        var dto = new CadastrarClienteDto(cliente.getEmail().getValue(),cliente.getCpf().getValue(), cliente.getNome());
        when(clienteGatewayPort.clienteExiste(
                new CPF(dto.getCpf()),
                new Email(dto.getEmail())
        )).thenReturn(true);


        // Act
        Exception exception = assertThrows(RecursoJaExisteException.class, () -> {
            useCase.execute(dto);
        });

        // Assert
        assertNotNull(exception);
        assertEquals("Email ou Cpf já possui cadastro", exception.getMessage());
    }
}
