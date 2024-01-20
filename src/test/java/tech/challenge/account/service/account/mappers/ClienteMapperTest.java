package tech.challenge.account.service.account.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.challenge.account.service.account.application.presenters.mappers.ClienteMapper;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.valueobjects.CPF;
import tech.challenge.account.service.account.domain.valueobjects.Email;
import tech.challenge.account.service.account.infrastructure.db.entities.ClienteEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteMapperTest {
    UUID id;
    String nome = "Nome";
    String email = "teste@email.com";
    String cpf = "766.153.228-03";


    @BeforeEach
    void setup() {
        id = UUID.randomUUID();
    }
    @Test
    void toDomain_deveRetornarCliente() {
        // Arrange
        var ClienteEntity = new ClienteEntity(nome, email, cpf);
        ClienteEntity.setId(id);

        // Act
        var result = new ClienteMapper().toDomain(ClienteEntity);

        // Assert
        assertEquals(nome, result.getNome());
        assertEquals(email, result.getEmail().getValue());
        assertEquals(cpf, result.getCpf().getValue());
        assertEquals(id, result.getId());
    }

    @Test
    void toEntity_deveRetornarClienteEntity() {
        // Arrange
        var cliente = new Cliente(id, nome, new Email(email), new CPF(cpf));
        cliente.setId(id);

        // Act
        var result = new ClienteMapper().toEntity(cliente);

        // Assert
        assertEquals(nome, result.getNome());
        assertEquals(email, result.getEmail());
        assertEquals(cpf, result.getCpf());
        assertEquals(id, result.getId());
    }
}
