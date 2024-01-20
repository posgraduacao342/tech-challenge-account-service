package tech.challenge.account.service.account.db.entities;

import org.junit.jupiter.api.Test;
import tech.challenge.account.service.account.infrastructure.db.entities.ClienteEntity;


import static org.junit.jupiter.api.Assertions.*;

public class ClienteEntityTest {
    // Arrange
    String nome = "Nome";
    String email = "teste@email.com";
    String cpf = "766.153.228-03";

    @Test
    void allArgsConstructor() {
        // Act
        var clienteEntity = new ClienteEntity(nome, email, cpf);

        // Assert
        assertEquals(nome, clienteEntity.getNome());
        assertEquals(email, clienteEntity.getEmail());
        assertEquals(cpf, clienteEntity.getCpf());
    }

    @Test
    void noArgsConstructor() {
        // Act
        var clienteEntity = new ClienteEntity();

        // Assert
        assertNull(clienteEntity.getCpf());
        assertNull(clienteEntity.getNome());
        assertNull(clienteEntity.getEmail());
    }
}
