package tech.challenge.account.service.account.application.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tech.challenge.account.service.account.application.controllers.ClienteController;
import tech.challenge.account.service.account.domain.dto.requets.CriarClienteRequest;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.exception.RecursoNaoEncontratoException;
import tech.challenge.account.service.account.domain.usecases.BuscarClientePorCPFUseCase;
import tech.challenge.account.service.account.domain.usecases.BuscarClientesUseCase;
import tech.challenge.account.service.account.domain.usecases.CadastrarClienteUseCase;
import tech.challenge.account.service.account.domain.usecases.DeletarClienteUseCase;
import tech.challenge.account.service.account.helpers.ClienteHelper;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import java.util.List;
import java.util.UUID;

import static java.text.MessageFormat.format;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ClienteControllerIT {

    @Autowired
    CadastrarClienteUseCase cadastrarClienteUseCase;

    @Autowired
    BuscarClientesUseCase buscarClientesUseCase;

    @Autowired
    BuscarClientePorCPFUseCase buscarClientePorCPFUseCase;

    @Autowired
    DeletarClienteUseCase deletarClienteUseCase;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteController clienteController;

    @Test
    void deveCadastrarCliente() {
        var cliente = ClienteHelper.gerarCliente();
        cliente.setId(null);
        var request = new CriarClienteRequest(cliente.getNome(), cliente.getEmail().getValue(), cliente.getCpf().getValue());

        var response = cadastrarClienteUseCase.execute(request);
        var clienteCriado = clienteRepository.findByCpf(request.getCpf());

        assertEquals(clienteCriado.get().getNome(), response.getNome());

        clienteRepository.deleteById(clienteCriado.get().getId());
    }

    @Test
    void buscarClientes_DeveRetornarListaDeClientes() {
        List<Cliente> result = buscarClientesUseCase.execute();

        assertEquals(5, result.size());
    }

    @Test
    void buscarClientePorCpf_DeveRetornarOCliente() {
        var cpf = "831.643.420-20";

        var cliente = buscarClientePorCPFUseCase.execute(cpf);

        assertEquals("Vick@gmail.com", cliente.getEmail().getValue());
        assertEquals(cliente.getCpf().getValue(), cpf);
    }

    @Test
    void buscarClientePorCpf_DeveRetornarExcecaoQuandoOClienteNaoForEncontrado() {
        var cpf = "697.525.870-89";

        Exception exception = assertThrows(RecursoNaoEncontratoException.class, () -> clienteController.buscarClientePorCpf(cpf));
        assertEquals(format("Registro não encontrado com cpf {0}", cpf), exception.getMessage());
    }

    @Test
    void deletarClientePorId_DeveDeletarOCliente() {
        var id = UUID.fromString("592ac344-9f12-40cd-8ed9-1fde6ad9006e");

        deletarClienteUseCase.execute(id);
        var cliente = clienteRepository.findById(id);

        assertTrue(cliente.isEmpty());
    }

    @Test
    void deletarClientePorId_DeveRetornarExcecaoQuandoClienteNaoForEncontrado() {
        var id = UUID.fromString("f0aed070-9000-4773-88cb-25f035fcc647");

        Exception exception = assertThrows(RecursoNaoEncontratoException.class, () -> clienteController.deletarCLiente("" + id));
        assertEquals(format("Registro não encontrado com id {0}", id), exception.getMessage());
    }
}
