package tech.challenge.account.service.account.application.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.valueObjects.CPF;
import tech.challenge.account.service.account.domain.valueObjects.Email;
import tech.challenge.account.service.account.helpers.ClienteHelper;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import java.util.List;
import java.util.UUID;

import static java.text.MessageFormat.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ClienteGatewayIT {

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

    @Test
    void buscarClientePorCPF_DeveRetornarOCliente() {
        // Arrange
        var cpf = new CPF("831.643.420-20");

        // Act
        var cliente = clienteGateway.buscarClientePorCpf(cpf);

        // Assert
        assertEquals("Vick@gmail.com", cliente.getEmail().getValue());
        assertEquals(cliente.getCpf().getValue(), cpf.getValue());
    }

    @Test
    void buscarClientePorCPF_DeveRetornarExcecaoQuandoOClienteNaoForEncontrado() {
        // Arrange
        var cpf = new CPF("697.525.870-89");

        // Act / Assert
        Exception exception = assertThrows(RuntimeException.class, () -> clienteGateway.buscarClientePorCpf(cpf));
        assertEquals(format("Registro não encontrado com cpf {0}",cpf.getValue()), exception.getMessage());
    }

    @Test
    void buscarClientePorEmail_DeveRetornarOCliente() {
        // Arrange
        var email = new Email("Adam@gmail.com");

        // Act
        var cliente = clienteGateway.buscarClientePorEmail(email);

        // Assert
        assertEquals("092.420.830-97", cliente.getCpf().getValue());
        assertEquals(cliente.getEmail().getValue(), email.getValue());
    }

    @Test
    void buscarClientePorEmail_DeveRetornarExcecaoQuandoOClienteNaoForEncontrado() {
        // Arrange
        var email = new Email("fake@gmail.com");

        // Act / Assert
        Exception exception = assertThrows(RuntimeException.class, () -> clienteGateway.buscarClientePorEmail(email));
        assertEquals(format("Registro não encontrado com email {0}",email.getValue()), exception.getMessage());
    }

    @Test
    void cadastrarCliente_DeveCadastrarOCliente() {
        // Arrange
        var cliente = ClienteHelper.gerarCliente();

        // Act
        clienteGateway.cadastrarCliente(cliente);
        var result = clienteGateway.buscarClientePorCpf(cliente.getCpf());

        // Assert
        assertEquals(result.getCpf().getValue(), cliente.getCpf().getValue());
        assertEquals(result.getNome(), cliente.getNome());
    }

    @Test
    void deletarClientePorId_DeveDeletarOCliente() {
        // Arrange
        var id = UUID.fromString("592ac344-9f12-40cd-8ed9-1fde6ad9006e");

        // Act
        clienteGateway.deletarCliente(id);
        var cliente = clienteRepository.findById(id);

        // Assert
        assertTrue(cliente.isEmpty());
    }

    @Test
    void deletarClientePorId_DeveRetornarExcecaoQuandoClienteNaoForEncontrado() {
        //Arrange
        var id = UUID.fromString("f0aed070-9000-4773-88cb-25f035fcc647");

        // Act / Assert
        Exception exception = assertThrows(RuntimeException.class, () -> clienteGateway.deletarCliente(id));
        assertEquals(format("Registro não encontrado com id {0}", id), exception.getMessage());
    }
}
