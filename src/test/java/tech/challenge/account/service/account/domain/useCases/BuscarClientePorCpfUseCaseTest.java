//package tech.challenge.account.service.account.domain.useCases;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import tech.challenge.account.service.account.application.gateway.ClienteGateway;
//import tech.challenge.account.service.account.domain.entities.Cliente;
//import tech.challenge.account.service.account.domain.exception.RecursoNaoEncontratoException;
//import tech.challenge.account.service.account.domain.valueObjects.CPF;
//import tech.challenge.account.service.account.domain.
//import tech.challenge.account.service.account.helpers.ClienteHelper;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class BuscarClientePorCpfUseCaseTest {
//    @Mock
//    ClienteGateway gateway;
//    BuscarClientePorCPFUseCase useCase;
//    AutoCloseable openMocks;
//
//    @BeforeEach
//    void setup() {
//        openMocks = MockitoAnnotations.openMocks(this);
//        useCase = new BuscarClientePorCPFUseCase(gateway);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//        openMocks.close();
//    }
//
//    @Test
//    void deveRetornarUmClientes() {
//        // Arrange
//        var cliente = ClienteHelper.gerarCliente();
//        when(gateway.buscarClientePorCpf(cliente.getCpf())).thenReturn(cliente);
//
//        // Act
//        var result = useCase.execute(cliente.getCpf().getValue());
//
//        // Assert
//        assertEquals(result, cliente);
//        verify(gateway, times(1)).buscarClientes();
//    }
//
//    @Test
//    void deveRetornarNotFoundException() {
//        // Arrange
//        when(gateway.buscarClientePorCpf(new CPF("882.507.488-32"))).thenThrow(RecursoNaoEncontratoException.class);
//
//        // Act
//        Exception exception = assertThrows(RecursoNaoEncontratoException.class, () -> {
//            useCase.execute("118.457.876-17");
//        });
//
//        // Assert
//        assertNotNull(exception);
//    }
//}
