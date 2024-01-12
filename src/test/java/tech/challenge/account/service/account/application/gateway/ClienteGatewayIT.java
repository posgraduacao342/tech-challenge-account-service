package tech.challenge.account.service.account.application.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ClienteGatewayIT {

    @Autowired
    private ClienteGateway clienteGateway;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void devePermitirCriarTabela() {
        var totalDeRegistros = clienteRepository.count();
        assertThat(totalDeRegistros).isNotNegative();
    }

    @Test
    void buscarClientes_DeveRetornarListaDeClientes() {
        // Act
        List<Cliente> resultado = clienteGateway.buscarClientes();

        // Assert
        assertEquals(5, resultado.size());
    }

}
