package tech.challenge.account.service.account.application.gateway;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.challenge.account.service.account.application.presenters.mappers.ClienteMapper;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.exception.RecursoNaoEncontratoException;
import tech.challenge.account.service.account.domain.ports.out.ClienteGatewayPort;
import tech.challenge.account.service.account.domain.valueObjects.CPF;
import tech.challenge.account.service.account.domain.valueObjects.Email;
import tech.challenge.account.service.account.infrastructure.db.entities.ClienteEntity;
import tech.challenge.account.service.account.infrastructure.db.repositories.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static java.text.MessageFormat.format;

@Component
@AllArgsConstructor
public class ClienteGateway implements ClienteGatewayPort {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    @Override
    public Cliente buscarClientePorCpf(CPF cpf) throws RecursoNaoEncontratoException {
        var clienteEntity = clienteRepository.findByCpf(cpf.getValue())
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com cpf {0}", cpf.getValue())));

        return clienteMapper.toDomain(clienteEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente buscarClientePorEmail(Email email) throws RecursoNaoEncontratoException {
        var clienteEntity = clienteRepository.findByEmail(email.getValue())
                .orElseThrow(() -> new RecursoNaoEncontratoException(format("Registro não encontrado com email {0}", email.getValue())));

        return clienteMapper.toDomain(clienteEntity);
    }

    @Transactional
    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        var clienteEntity = clienteMapper.toEntity(cliente);
        return clienteMapper.toDomain(clienteRepository.save(clienteEntity));
    }

    @Transactional
    @Override
    public void deletarCliente(UUID id) {
        this.clienteRepository.deleteById(id);
    }
}
