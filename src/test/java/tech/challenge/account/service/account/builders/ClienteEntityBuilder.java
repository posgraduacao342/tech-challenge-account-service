package tech.challenge.account.service.account.builders;

import tech.challenge.account.service.account.infrastructure.db.entities.ClienteEntity;

public class ClienteEntityBuilder {
    public static ClienteEntity Build(){
        return ClienteEntity.builder()
            .cpf("11111")
            .email("test@gmail.com")
            .nome("Test")
            .build();
    }
}
