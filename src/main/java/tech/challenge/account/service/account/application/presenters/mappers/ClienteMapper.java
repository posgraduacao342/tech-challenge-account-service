package tech.challenge.account.service.account.application.presenters.mappers;

import org.springframework.stereotype.Component;
import tech.challenge.account.service.account.domain.dto.responses.ClienteResponse;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.valueobjects.CPF;
import tech.challenge.account.service.account.domain.valueobjects.Email;
import tech.challenge.account.service.account.infrastructure.db.entities.ClienteEntity;

import java.util.ArrayList;
import java.util.List;

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

    public ClienteResponse toResponse(Cliente cliente) {
        var response = new ClienteResponse();
        response.setCpf(cliente.getCpf().getValue());
        response.setEmail(cliente.getEmail().getValue());
        response.setNome(cliente.getNome());
        response.setId(cliente.getId());
        return response;
    }

    public List<ClienteResponse> toReposnse(List<Cliente> clienteList) {
        var response = new ArrayList<ClienteResponse>();
        clienteList.forEach(item -> response.add(toResponse(item)));
        return response;
    }
}
