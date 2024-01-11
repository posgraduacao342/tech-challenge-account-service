package tech.challenge.account.service.account.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.challenge.account.service.account.builders.ClienteEntityBuilder;
import tech.challenge.account.service.account.infrastructure.db.entities.ClienteEntity;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ClienteRepositoryTest {

    @Mock
    private ClienteRepository clienteRepository;

    ClienteEntity clienteMock;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        clienteMock = ClienteEntityBuilder.Build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCriarCliente() {
        //Arrange
        when(clienteRepository.save(clienteMock)).thenReturn(clienteMock);

        //Act
        var clienteArmazenado = clienteRepository.save(clienteMock);

        assertThat(clienteArmazenado)
                .isNotNull()
                .isEqualTo(clienteMock);

        verify(clienteRepository, times(1)).save(clienteMock);
    }

    @Test
    void deveAtualizarCliente() {
        //Arrange
        var id = UUID.randomUUID();
        var email = "testeatualizar@email.com";
        clienteMock.setId(id);
        var clienteAtualizadoEsperado = clienteMock;
        clienteAtualizadoEsperado.setEmail(email);
        when(clienteRepository.findById(id)).thenReturn(Optional.ofNullable(clienteMock));
        when(clienteRepository.save(clienteMock)).thenReturn(clienteMock);

        //Act
        var clienteEntityOptional = clienteRepository.findById(id);
        clienteEntityOptional.get().setEmail(email);
        var clienteAtualizado = clienteRepository.save(clienteEntityOptional.get());

        //Asserts
        assertThat(clienteAtualizado).isNotNull();
        assertThat(clienteAtualizadoEsperado).isEqualTo(clienteAtualizado);
        verify(clienteRepository, times(1)).findById(any(UUID.class));
        verify(clienteRepository, times(1)).save(clienteMock);
    }

    @Test
    void deveDeletarCliente() {
        //Arrange
        var id = UUID.fromString("cc2f2531-76ac-4dce-a7ad-4beeacb46d50");
        clienteMock.setId(id);
        //Act
        clienteRepository.deleteById(id);
        //Assert
        verify(clienteRepository, times(1)).deleteById(id);
    }

    @Test
    void deveBuscarClientePorCpf() {
        //Arrange
        when(clienteRepository.findByCpf(clienteMock.getCpf())).thenReturn(Optional.of(clienteMock));

        //Act
        var cliente = clienteRepository.findByCpf(clienteMock.getCpf());

        //Assert
        assertThat(cliente.isPresent()).isTrue();
        assertThat(cliente.get()).isEqualTo(clienteMock);
    }

    @Test
    void deveBuscarClientePorEmail() {
        //Arrange
        when(clienteRepository.findByEmail(clienteMock.getEmail())).thenReturn(Optional.of(clienteMock));

        //Act
        var cliente = clienteRepository.findByEmail(clienteMock.getEmail());

        //Assert
        assertThat(cliente.isPresent()).isTrue();
        assertThat(cliente.get()).isEqualTo(clienteMock);
    }

    @Test
    void deveBuscarTodosOsClientes() {
        // Arrange
        var cliente1 = clienteMock;
        var cliente2 = clienteMock;
        var clientList = Arrays.asList(cliente1, cliente2);
        when(clienteRepository.findAll()).thenReturn(clientList);

        //Act
        var resposta = clienteRepository.findAll();

        //Assert
        verify(clienteRepository, times(1)).findAll();
        assertThat(resposta).hasSize(2);
    }
}
