package tech.challenge.account.service.account.application.presenters.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CriarClienteResponse {
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
}
