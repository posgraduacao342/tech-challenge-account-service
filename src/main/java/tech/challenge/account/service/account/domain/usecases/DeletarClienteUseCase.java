package tech.challenge.account.service.account.domain.usecases;

import lombok.AllArgsConstructor;
import tech.challenge.account.service.account.application.gateway.ClienteGateway;
import tech.challenge.account.service.account.domain.ports.in.UseCaseOnlyRequestPort;

import java.util.UUID;

@AllArgsConstructor
public class DeletarClienteUseCase implements UseCaseOnlyRequestPort<UUID> {
    private final ClienteGateway clienteGateway;

    @Override
    public void execute(UUID id) {
        clienteGateway.deletarCliente(id);
    }
}
