package tech.challenge.account.service.account.domain.entities;

import lombok.*;
import tech.challenge.account.service.account.domain.valueobjects.CPF;
import tech.challenge.account.service.account.domain.valueobjects.Email;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente {
    private UUID id;
    private String nome;
    private Email email;
    private CPF cpf;
}
