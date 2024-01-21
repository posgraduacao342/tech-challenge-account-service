package tech.challenge.account.service.account.application.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.challenge.account.service.account.application.presenters.mappers.ClienteMapper;
import tech.challenge.account.service.account.domain.dto.requets.CriarClienteRequest;
import tech.challenge.account.service.account.domain.dto.responses.CriarClienteResponse;
import tech.challenge.account.service.account.domain.exception.RecursoJaExisteException;
import tech.challenge.account.service.account.domain.exception.RecursoNaoEncontratoException;
import tech.challenge.account.service.account.domain.usecases.BuscarClientePorCPFUseCase;
import tech.challenge.account.service.account.domain.usecases.BuscarClientesUseCase;
import tech.challenge.account.service.account.domain.usecases.CadastrarClienteUseCase;
import tech.challenge.account.service.account.domain.usecases.DeletarClienteUseCase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {
    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final ClienteMapper clienteMapper;
    private final BuscarClientesUseCase buscarClientesUseCase;
    private final BuscarClientePorCPFUseCase buscarClientePorCPFUseCase;
    private final DeletarClienteUseCase deletarClienteUseCase;

    @PostMapping
    public CriarClienteResponse cadastrarCliente(@RequestBody @Valid CriarClienteRequest clienteRequest) throws RecursoJaExisteException {
        var clienteCriado = cadastrarClienteUseCase.execute(clienteRequest);
        return clienteMapper.toResponse(clienteCriado);
    }

    @GetMapping
    public List<CriarClienteResponse> buscarClientes() {
        var clientes = buscarClientesUseCase.execute();
        return clienteMapper.toReposnse(clientes);
    }

    @GetMapping("/{cpf}")
    public CriarClienteResponse buscarClientePorCpf(@PathVariable(value = "cpf") String cpf) throws RecursoNaoEncontratoException {
        var cliente = buscarClientePorCPFUseCase.execute(cpf);
        return clienteMapper.toResponse(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public void deletarCLiente(@PathVariable(value = "clienteId") String clienteId) {
        deletarClienteUseCase.execute(UUID.fromString(clienteId));
    }
}
