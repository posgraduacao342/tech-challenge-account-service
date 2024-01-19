package tech.challenge.account.service.account.domain.useCases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.challenge.account.service.account.application.gateway.ClienteGateway;
import tech.challenge.account.service.account.domain.exception.RecursoNaoEncontratoException;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import java.util.UUID;

import static java.text.MessageFormat.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeletarClienteUseCaseTest {
    @Mock
    ClienteGateway gateway;
    AutoCloseable openMocks;
    DeletarClienteUseCase useCase;
    @Mock
    ClienteRepository clienteRepository;


    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        AutoCloseable openMocks;
        useCase = new DeletarClienteUseCase(gateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveDeletarCliente() {
        //Arange
        var id = UUID.randomUUID();
        //Act
        useCase.execute(id);
        //Assert
        verify(gateway, times(1)).deletarCliente(id);
    }

    @Test
    void deveRetornarExcecaoQuandoClienteNaoForEncontrado() {
        //Arange
        var id = UUID.randomUUID();
        willThrow(new RecursoNaoEncontratoException(format("Registro não encontrado com id {0}", id))).given(gateway).deletarCliente(id);

        // Assert
        Exception exception = assertThrows(RecursoNaoEncontratoException.class, () -> useCase.execute(id));
        assertEquals(format("Registro não encontrado com id {0}", id), exception.getMessage());
        verify(gateway, times(1)).deletarCliente(id);
    }
}
