package tech.challenge.account.service.account.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tech.challenge.account.service.account.helpers.ClienteEntityHelper;
import tech.challenge.account.service.account.infrastructure.db.entities.ClienteEntity;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class ClienteRepositoryIT {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void devePermitirCriarTabela() {
        var totalDeRegistros = clienteRepository.count();
        assertThat(totalDeRegistros).isNotNegative();
    }

    @Test
    void devePermitirCriarCliente() {
        // Arrange
        var cliente = ClienteEntityHelper.gerarClienteEntity();

        // Act
        var clienteCriado = clienteRepository.save(cliente);

        // Assert
        assertThat(clienteCriado)
                .isInstanceOf(ClienteEntity.class)
                .isNotNull();

        assertThat(clienteCriado.getId())
                .isNotNull();

        assertThat(clienteCriado.getNome())
                .isEqualTo(cliente.getNome());

        assertThat(clienteCriado.getCpf())
                .isEqualTo(cliente.getCpf());

        assertThat(clienteCriado.getEmail())
                .isEqualTo(cliente.getEmail());

        clienteRepository.delete(clienteCriado);
    }

    @Test
    void deveDeletarCliente() {
        // Arrange
        var cliente = registrarCliente();
        var id = cliente.getId();

        // Act
        clienteRepository.deleteById(id);
        var clienteOptional = clienteRepository.findById(id);

        // Assert
        assertThat(clienteOptional)
                .isEmpty();
    }

    @Test
    void deveBuscarClientePorCpf() {
        //Arrange
        //Act
        var clienteEncontradoOpt = clienteRepository.findByCpf("092.420.830-97");

        //Assert
        Assertions.assertThat(clienteEncontradoOpt.isPresent()).isTrue();
        var clienteEncontrado = clienteEncontradoOpt.get();
        Assertions.assertThat(clienteEncontrado.getNome()).isEqualTo("Adam");
        Assertions.assertThat(clienteEncontrado.getEmail()).isEqualTo("Adam@gmail.com");
        Assertions.assertThat(clienteEncontrado.getId().toString()).isEqualTo("5f789b39-4295-42c1-a65b-cfca5b987db2");
    }

    @Test
    void deveBuscarClientePorEmail() {
        //Arrange
        //Act
        var clienteEncontradoOpt = clienteRepository.findByEmail("Adam@gmail.com");
        //Assert
        Assertions.assertThat(clienteEncontradoOpt.isPresent()).isTrue();
        var clienteEncontrado = clienteEncontradoOpt.get();
        Assertions.assertThat(clienteEncontrado.getNome()).isEqualTo("Adam");
        Assertions.assertThat(clienteEncontrado.getCpf()).isEqualTo("092.420.830-97");
        Assertions.assertThat(clienteEncontrado.getId().toString()).isEqualTo("5f789b39-4295-42c1-a65b-cfca5b987db2");
    }

    @Test
    void deveBuscarTodosOsClientes() {
        //Arrange
        //Act
        var resposta = clienteRepository.findAll();
        //Assert
        assertThat(resposta).hasSize(5);
    }

    private ClienteEntity registrarCliente() {
        var cliente = ClienteEntityHelper.gerarClienteEntity();
        cliente.setId(UUID.fromString("cc2f2531-76ac-4dce-a7ad-4beeacb46d50"));
        return clienteRepository.save(cliente);
    }
}
