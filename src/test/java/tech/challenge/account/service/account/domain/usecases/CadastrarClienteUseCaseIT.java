package tech.challenge.account.service.account.domain.usecases;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tech.challenge.account.service.account.domain.dto.requets.CriarClienteRequest;
import tech.challenge.account.service.account.domain.exception.RecursoJaExisteException;
import tech.challenge.account.service.account.domain.ports.out.ClienteGatewayPort;
import tech.challenge.account.service.account.domain.valueobjects.CPF;
import tech.challenge.account.service.account.domain.valueobjects.Email;
import tech.challenge.account.service.account.helpers.ClienteHelper;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class CadastrarClienteUseCaseIT {
    @Autowired
    ClienteGatewayPort clienteGatewayPort;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    CadastrarClienteUseCase useCase;

    @Test
    void devePermitirCriarTabela() {
        var totalDeRegistros = clienteRepository.count();
        assertThat(totalDeRegistros).isNotNegative();
    }

    @Test
    void deveCadastrarUmCliente() {
        // Arrange
        var cliente = ClienteHelper.gerarCliente();
        cliente.setId(null);
        var request = new CriarClienteRequest(cliente.getNome(), cliente.getEmail().getValue(), cliente.getCpf().getValue());

        // Act
        var response = useCase.execute(request);
        var clienteCriado = clienteRepository.findByCpf(request.getCpf());

        // Assert
        assertEquals(clienteCriado.get().getNome(), response.getNome());

        clienteRepository.deleteById(clienteCriado.get().getId());
    }

    @Test
    void deveLancarException_QuandoOEmailJaExistir() {
        // Arrange
        var cliente = ClienteHelper.gerarCliente();
        cliente.setEmail(new Email("Adam@gmail.com"));
        var request = new CriarClienteRequest(cliente.getNome(), cliente.getEmail().getValue(), cliente.getCpf().getValue());

        // Act
        Exception exception = assertThrows(RecursoJaExisteException.class, () -> useCase.execute(request));

        // Assert
        assertNotNull(exception);
        assertEquals("Email ou Cpf já possui cadastro", exception.getMessage());
    }

    @Test
    void deveLancarException_QuandoOCPFJaExistir() {
        // Arrange
        var cliente = ClienteHelper.gerarCliente();
        cliente.setCpf(new CPF("092.420.830-97"));
        var request = new CriarClienteRequest(cliente.getNome(), cliente.getEmail().getValue(), cliente.getCpf().getValue());

        // Act
        Exception exception = assertThrows(RecursoJaExisteException.class, () -> useCase.execute(request));

        // Assert
        assertNotNull(exception);
        assertEquals("Email ou Cpf já possui cadastro", exception.getMessage());
    }
}
