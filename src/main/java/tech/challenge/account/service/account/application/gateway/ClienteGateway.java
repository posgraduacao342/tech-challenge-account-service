package tech.challenge.account.service.account.application.gateway;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import tech.challenge.account.service.account.application.presenters.mappers.ClienteMapper;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.ports.out.ClienteGatewayPort;
import tech.challenge.account.service.account.infrastructure.db.entities.ClienteEntity;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ClienteGateway implements ClienteGatewayPort {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public List<Cliente> buscarClientes() {
        var clientesEntity = this.clienteRepository.findAll();

        if (clientesEntity == null) {
            return new ArrayList<>();
        }

        List<Cliente> clientes = new ArrayList<Cliente>();

        for (ClienteEntity clienteEntity : clientesEntity) {
            var cliente = clienteMapper.toDomain(clienteEntity);
            if (cliente != null) {
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
