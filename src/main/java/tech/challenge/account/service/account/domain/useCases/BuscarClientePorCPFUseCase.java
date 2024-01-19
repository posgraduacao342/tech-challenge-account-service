package tech.challenge.account.service.account.domain.useCases;

import lombok.AllArgsConstructor;
import tech.challenge.account.service.account.application.gateway.ClienteGateway;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.exception.RecursoNaoEncontratoException;
import tech.challenge.account.service.account.domain.ports.in.UseCasePort;
import tech.challenge.account.service.account.domain.valueObjects.CPF;

@AllArgsConstructor
public class BuscarClientePorCPFUseCase implements UseCasePort<Cliente, String> {
    private final ClienteGateway clienteGateway;

    @Override
    public Cliente execute(String cpf) throws RecursoNaoEncontratoException {
        return clienteGateway.buscarClientePorCpf(new CPF(cpf));
    }
}