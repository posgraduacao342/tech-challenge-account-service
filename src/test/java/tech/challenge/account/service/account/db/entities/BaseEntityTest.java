package tech.challenge.account.service.account.db.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import tech.challenge.account.service.account.infrastructure.db.entities.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseEntityTest {
    // Arrange
    UUID id;
    LocalDateTime dataCriacao;
    LocalDateTime dataAtualizacao;
    LocalDateTime dataDelecao;

    @BeforeEach
    void setup() {
        id = UUID.randomUUID();
        var now = LocalDateTime.now();
        dataCriacao = now.minusDays(1);
        dataAtualizacao = now.minusDays(1);
        dataDelecao = now;
    }
    @Test
    void allArgsConstructor() {
        // Act
        var baseEntity = new BaseEntity();
        baseEntity.setId(id);
        baseEntity.setDataCriacao(dataCriacao);
        baseEntity.setDataAtualizacao(dataAtualizacao);
        baseEntity.setDataDelecao(dataDelecao);

        // Assert
        assertEquals(id, baseEntity.getId());
        assertEquals(dataCriacao, baseEntity.getDataCriacao());
        assertEquals(dataAtualizacao, baseEntity.getDataAtualizacao());
        assertEquals(dataDelecao, baseEntity.getDataDelecao());
    }

    @Test
    void noArgsConstructor() {
        // Act
        var baseEntity = new BaseEntity();

//        // Assert
//        assertNull(baseEntity.getCpf());
//        assertNull(baseEntity.getNome());
//        assertNull(baseEntity.getEmail());
    }
}
