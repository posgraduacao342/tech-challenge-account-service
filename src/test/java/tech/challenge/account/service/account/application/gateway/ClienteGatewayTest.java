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
import java.util.Optional;

import static java.text.MessageFormat.format;
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

    @Test
    void buscarClientePorCPF_DeveRetornarCliente() {
        // Arrange
        var cliente = ClienteHelper.gerarCliente();
        var clienteEntity = ClienteEntityHelper.gerarClienteEntity(cliente);

        when(clienteRepository.findByCpf(clienteEntity.getCpf())).thenReturn(Optional.of(clienteEntity));
        when(clienteMapper.toDomain(Mockito.any(ClienteEntity.class))).thenReturn(cliente);

        // Act
        var resultado = clienteGateway.buscarClientePorCpf(cliente.getCpf());

        // Assert
        assertEquals(cliente.getEmail(), resultado.getEmail());
        assertEquals(cliente.getNome(),resultado.getNome());
        assertEquals(cliente.getCpf(), resultado.getCpf());
        verify(clienteRepository, times(1)).findByCpf(clienteEntity.getCpf());
    }

    @Test
    void buscarClientePorCPF_DeveRetornarExcecaoQuandoOClienteNaoForEncontrado() {
        // Arrange
        var clienteEntity = ClienteEntityHelper.gerarClienteEntity();
        var cliente = ClienteHelper.gerarCliente();
        var cpf = cliente.getCpf();
        when(clienteRepository.findByCpf(clienteEntity.getCpf())).thenReturn(Optional.empty());

        String mensagemEsperada = format("Registro não encontrado com cpf {0}", cliente.getCpf().getValue());

        // Act / Assert
        assertThrows(RuntimeException.class, () -> clienteGateway.buscarClientePorCpf(cpf), mensagemEsperada);
    }

    @Test
    void buscarClientePorEmail_DeveRetornarCliente() {
        // Arrange
        var clienteEntity = ClienteEntityHelper.gerarClienteEntity();

        var cliente = ClienteHelper.gerarCliente();

        when(clienteRepository.findByEmail(clienteEntity.getEmail())).thenReturn(Optional.of(clienteEntity));
        when(clienteMapper.toDomain(Mockito.any(ClienteEntity.class))).thenReturn(cliente);

        // Act
        var resultado = clienteGateway.buscarClientePorEmail(cliente.getEmail());

        // Assert
        assertEquals(cliente.getEmail(), resultado.getEmail());
        assertEquals(cliente.getNome(),resultado.getNome());
        assertEquals(cliente.getCpf(), resultado.getCpf());
        verify(clienteRepository, times(1)).findByEmail(clienteEntity.getEmail());
    }

    @Test
    void buscarClientePorEmail_DeveRetornarExcecaoQuandoOClienteNaoForEncontrado() {
        // Arrange
        var clienteEntity = ClienteEntityHelper.gerarClienteEntity();
        var cliente = ClienteHelper.gerarCliente();
        var email = cliente.getEmail();
        when(clienteRepository.findByCpf(clienteEntity.getCpf())).thenReturn(Optional.empty());

        // Act / Assert
        Exception exception = assertThrows(RuntimeException.class, () -> clienteGateway.buscarClientePorEmail(email));
        assertEquals(format("Registro não encontrado com email {0}", cliente.getEmail().getValue()), exception.getMessage());
    }

    @Test
    void cadastrarCliente_DeveCadastrarOCliente() {
        // Arrange
        var clienteEntity = ClienteEntityHelper.gerarClienteEntity();
        var cliente = ClienteHelper.gerarCliente();
        when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        when(clienteMapper.toEntity(Mockito.any(Cliente.class))).thenReturn(clienteEntity);
        when(clienteMapper.toDomain(Mockito.any(ClienteEntity.class))).thenReturn(cliente);

        // Act
        var resultado = clienteGateway.cadastrarCliente(cliente);

        // Assert
        assertEquals(cliente.getNome(), resultado.getNome());
        assertEquals(cliente.getEmail().getValue(), resultado.getEmail().getValue());
        assertEquals(cliente.getCpf().getValue(), resultado.getCpf().getValue());
        verify(clienteRepository, times(1)).save(clienteEntity);
    }

    @Test
    void deletarClientePorId_DeveDeletarOCliente() {
        // Arrange
        var cliente = ClienteHelper.gerarCliente();
        when(clienteRepository.existsById(cliente.getId())).thenReturn(true);

        // Act
        clienteGateway.deletarCliente(cliente.getId());

        // Act / Assert
        verify(clienteRepository, times(1)).existsById(cliente.getId());
        verify(clienteRepository, times(1)).deleteById(cliente.getId());
    }

    @Test
    void deletarClientePorId_DeveRetornarExcecaoQuandoClienteNaoForEncontrado() {
        //Arrange
        var cliente = ClienteHelper.gerarCliente();
        var id = cliente.getId();
        when(clienteRepository.existsById(cliente.getId())).thenReturn(false);

        // Act / Assert
        Exception exception = assertThrows(RuntimeException.class, () -> clienteGateway.deletarCliente(id));
        assertEquals(format("Registro não encontrado com id {0}", cliente.getId()), exception.getMessage());
        verify(clienteRepository, times(1)).existsById(cliente.getId());
    }
}
