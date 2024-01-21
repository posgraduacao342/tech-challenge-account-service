package tech.challenge.account.service.account.domain.usecases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.challenge.account.service.account.domain.dto.requets.CriarClienteRequest;
import tech.challenge.account.service.account.domain.exception.RecursoJaExisteException;
import tech.challenge.account.service.account.domain.ports.out.ClienteGatewayPort;
import tech.challenge.account.service.account.domain.valueobjects.CPF;
import tech.challenge.account.service.account.domain.valueobjects.Email;
import tech.challenge.account.service.account.helpers.ClienteHelper;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastrarClienteUseCaseTest {
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
        var request = new CriarClienteRequest(cliente.getNome(), cliente.getEmail().getValue(), cliente.getCpf().getValue());
        when(clienteGatewayPort.clienteExiste(cliente.getCpf(), cliente.getEmail())).thenReturn(false);
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
        var request = new CriarClienteRequest(cliente.getNome(), cliente.getEmail().getValue(), cliente.getCpf().getValue());
        when(clienteGatewayPort.clienteExiste(
                new CPF(dto.getCpf()),
                new Email(dto.getEmail())
        )).thenReturn(true);


        // Act
        Exception exception = assertThrows(RecursoJaExisteException.class, () -> useCase.execute(dto));

        // Assert
        assertNotNull(exception);
        assertEquals("Email ou Cpf já possui cadastro", exception.getMessage());
    }
}
