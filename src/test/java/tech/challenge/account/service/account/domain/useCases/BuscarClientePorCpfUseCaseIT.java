package tech.challenge.account.service.account.domain.useCases;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tech.challenge.account.service.account.application.gateway.ClienteGateway;
import tech.challenge.account.service.account.helpers.ClienteHelper;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class BuscarClientePorCpfUseCaseIT {
    @Autowired
    private ClienteGateway clienteGateway;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BuscarClientePorCPFUseCase useCase;

    @Test
    void deveRetornarUmCliente() {
        // Arrange
        var cliente = ClienteHelper.gerarClienteAdam();

        // Act
        var result = useCase.execute(cliente.getCpf().getValue());

        // Assert
        assertEquals(result, cliente);
    }
}