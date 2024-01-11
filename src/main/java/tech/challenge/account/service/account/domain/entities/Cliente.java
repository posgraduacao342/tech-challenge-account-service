package tech.challenge.account.service.account.domain.entities;

import lombok.Builder;
import tech.challenge.account.service.account.domain.valueObjects.CPF;
import tech.challenge.account.service.account.domain.valueObjects.Email;

import java.util.UUID;

@Builder
public class Cliente {
    private UUID id;
    private String nome;
    private Email email;
    private CPF cpf;
}
