package tech.challenge.account.service.account.domain.usecases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.ports.out.ClienteGatewayPort;
import tech.challenge.account.service.account.helpers.ClienteHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BuscarClientesUseCaseTest {

    @Mock
    ClienteGatewayPort clienteGatewayPort;

    BuscarClientesUseCase useCase;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarClientesUseCase(clienteGatewayPort);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveRetornarUmaListaDeClientes() {
        // Arrange
        var cliente = ClienteHelper.gerarCliente();
        var lista = Collections.singletonList(cliente);
        when(clienteGatewayPort.buscarClientes()).thenReturn(lista);

        // Act
        var clientes = useCase.execute();

        // Assert
        assertEquals(clientes.size(), lista.size());
        verify(clienteGatewayPort, times(1)).buscarClientes();
    }

    @Test
    void deveRetornarUmaListaDeVazia() {
        // Arrange
        List<Cliente> listaVazia = new ArrayList<>();

        when(clienteGatewayPort.buscarClientes()).thenReturn(listaVazia);

        // Act
        var clientes = useCase.execute();

        // Assert
        assertEquals(clientes.size(), listaVazia.size());
        verify(clienteGatewayPort, times(1)).buscarClientes();
    }
}
