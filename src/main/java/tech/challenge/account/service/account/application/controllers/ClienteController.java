package tech.challenge.account.service.account.application.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.challenge.account.service.account.application.presenters.mappers.ClienteMapper;
import tech.challenge.account.service.account.application.presenters.requets.CriarClienteRequest;
import tech.challenge.account.service.account.application.presenters.responses.CriarClienteResponse;
import tech.challenge.account.service.account.domain.dto.CadastrarClienteDto;
import tech.challenge.account.service.account.domain.exception.RecursoJaExisteException;
import tech.challenge.account.service.account.domain.usecases.CadastrarClienteUseCase;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {
    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final ClienteMapper clienteMapper;

    @PostMapping
    public CriarClienteResponse cadastrarCliente(@RequestBody @Valid CriarClienteRequest clienteRequest) throws RecursoJaExisteException {
        var clienteCriado = cadastrarClienteUseCase.execute(
                new CadastrarClienteDto(clienteRequest.getEmail(), clienteRequest.getCpf(), clienteRequest.getNome())
        );

        return clienteMapper.toResponse(clienteCriado);
    }
}
