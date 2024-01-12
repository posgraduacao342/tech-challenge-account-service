package tech.challenge.account.service.account.domain.useCases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.challenge.account.service.account.application.gateway.ClienteGateway;
import tech.challenge.account.service.account.helpers.ClienteHelper;

import static org.mockito.Mockito.*;

public class DeletarClienteUseCaseTest {
    @Mock
    ClienteGateway gateway;
    AutoCloseable openMocks;


    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        AutoCloseable openMocks;
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
     }

     @Test
     void deveDeletarCliente() {
         //Arange
         var cliente = ClienteHelper.gerarCliente();
         //Act
         gateway.deletarCliente(cliente.getId());
         //Assert
         verify(gateway, times(1)).deletarCliente(cliente.getId());
     }

    @Test
    void deveRetornarExcecaoQuandoClienteNaoForEncontrado() {
        //Arange
        var cliente = ClienteHelper.gerarCliente();
        //Act
        gateway.deletarCliente(cliente.getId());
        //Assert
        verify(gateway, times(1)).deletarCliente(cliente.getId());
    }

 }
