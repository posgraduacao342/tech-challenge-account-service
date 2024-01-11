package tech.challenge.account.service.account.application.gateway;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tech.challenge.account.service.account.application.presenters.mappers.ClienteMapper;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.helpers.ClienteEntityHelper;
import tech.challenge.account.service.account.helpers.ClienteHelper;
import tech.challenge.account.service.account.infrastructure.db.entities.ClienteEntity;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteGatewayTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    private ClienteGateway clienteGateway;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        clienteGateway = new ClienteGateway(clienteRepository, clienteMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


    @Test
    void buscarClientes_DeveRetornarListaDeClientes() {
        // Arrange
        List<ClienteEntity> clientesEntity = new ArrayList<>();
        clientesEntity.add(ClienteEntityHelper.gerarClienteEntity());

        var clienteMock = ClienteHelper.gerarCliente();

        List<Cliente> clientesMapeados = new ArrayList<>();
        clientesMapeados.add(ClienteHelper.gerarCliente());

        when(clienteRepository.findAll()).thenReturn(clientesEntity);
        when(clienteMapper.toDomain(Mockito.any(ClienteEntity.class))).thenReturn(clienteMock);

        // Act
        List<Cliente> resultado = clienteGateway.buscarClientes();

        // Assert
        assertEquals(clientesMapeados.size(), resultado.size());
        assertEquals(clientesMapeados.get(0).getNome(), resultado.get(0).getNome());
        assertEquals(clientesMapeados.get(0).getEmail(), resultado.get(0).getEmail());
        assertEquals(clientesMapeados.get(0).getCpf(), resultado.get(0).getCpf());
    }

    @Test
    void buscarClientes_SemClientesDeveRetornarListaVazia() {
        // Arrange
        when(clienteRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Cliente> resultado = clienteGateway.buscarClientes();

        // Assert
        assertTrue(resultado.isEmpty());
    }

    @Test
    void buscarClientes_MapperRetornaNull_DeveRetornarListaVazia() {
        // Arrange
        List<ClienteEntity> clientesEntity = new ArrayList<>();
        clientesEntity.add(ClienteEntityHelper.gerarClienteEntity());

        when(clienteRepository.findAll()).thenReturn(clientesEntity);
        when(clienteMapper.toDomain(Mockito.any(ClienteEntity.class))).thenReturn(null);

        // Act
        List<Cliente> resultado = clienteGateway.buscarClientes();

        // Assert
        assertTrue(resultado.isEmpty());
    }


    @Test
    void buscarClientes_RepositoryLancaExcecao_DeveLancarExcecao() {
        // Arrange
        when(clienteRepository.findAll()).thenThrow(new RuntimeException("Erro no banco de dados"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> clienteGateway.buscarClientes());
    }

}
