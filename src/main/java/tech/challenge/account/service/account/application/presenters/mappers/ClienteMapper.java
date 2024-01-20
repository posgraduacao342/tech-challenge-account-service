package tech.challenge.account.service.account.application.presenters.mappers;

import org.springframework.stereotype.Component;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.valueObjects.CPF;
import tech.challenge.account.service.account.domain.valueObjects.Email;
import tech.challenge.account.service.account.infrastructure.db.entities.ClienteEntity;

@Component
public class ClienteMapper {
    public Cliente toDomain(ClienteEntity clienteEntity) {
        var email = new Email(clienteEntity.getEmail());
        var cpf = new CPF(clienteEntity.getCpf());

        return Cliente.builder()
                .nome(clienteEntity.getNome())
                .cpf(cpf)
                .id(clienteEntity.getId())
                .email(email)
                .build();
    }

    public ClienteEntity toEntity(Cliente cliente) {
        var clienteEntity = new ClienteEntity();
        clienteEntity.setId(cliente.getId());
        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setEmail(cliente.getEmail().getValue());
        clienteEntity.setCpf(cliente.getCpf().getValue());

        return clienteEntity;
    }

}
