package tech.challenge.account.service.account.domain.usecases;

import lombok.AllArgsConstructor;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.ports.in.UseCaseOnlyResponsePort;
import tech.challenge.account.service.account.domain.ports.out.ClienteGatewayPort;

import java.util.List;

@AllArgsConstructor
public class BuscarClientesUseCase implements UseCaseOnlyResponsePort<List<Cliente>> {
    private final ClienteGatewayPort clienteGatewayPort;

    @Override
    public List<Cliente> execute() {
        return this.clienteGatewayPort.buscarClientes();
    }
}