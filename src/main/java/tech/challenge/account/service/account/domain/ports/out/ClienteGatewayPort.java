package tech.challenge.account.service.account.domain.ports.out;

import tech.challenge.account.service.account.domain.entities.Cliente;

import java.util.List;

public interface ClienteGatewayPort {
    List<Cliente> buscarClientes();
}
