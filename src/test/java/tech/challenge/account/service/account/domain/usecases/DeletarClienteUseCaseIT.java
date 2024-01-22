package tech.challenge.account.service.account.domain.usecases;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import tech.challenge.account.service.account.application.gateway.ClienteGateway;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import java.util.UUID;

import static java.text.MessageFormat.format;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@ActiveProfiles("test")
class DeletarClienteUseCaseIT {
    @Autowired
    private ClienteGateway gateway;

    @Autowired
    private ClienteRepository clienteRepository;


    @Test
    void deveDeletarCliente() {
        // Arrange
        var id = UUID.fromString("592ac344-9f12-40cd-8ed9-1fde6ad9006e");

        // Act
        gateway.deletarCliente(id);
        var cliente = clienteRepository.findById(id);

        // Assert
        assertTrue(cliente.isEmpty());
    }

    @Test
    void deveRetornarExcecaoQuandoClienteNaoForEncontrado() {
        //Arrange
        var id = UUID.fromString("f0aed070-9000-4773-88cb-25f035fcc647");

        // Act / Assert
        Exception exception = assertThrows(RuntimeException.class, () -> gateway.deletarCliente(id));
        assertEquals(format("Registro não encontrado com id {0}", id), exception.getMessage());
    }


}
