package tech.challenge.account.service.account.domain.usecases;

import lombok.AllArgsConstructor;
import tech.challenge.account.service.account.application.gateway.ClienteGateway;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.exception.RecursoNaoEncontratoException;
import tech.challenge.account.service.account.domain.ports.in.UseCasePort;
import java.util.UUID;
@AllArgsConstructor
public class BuscarClientePorIdUseCase implements UseCasePort<Cliente, UUID> {
    private final ClienteGateway clienteGateway;

    @Override
    public Cliente execute(UUID clienteId) throws RecursoNaoEncontratoException {
        return clienteGateway.buscarClientePorId(clienteId);
    }
}