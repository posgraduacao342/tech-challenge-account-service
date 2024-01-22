package tech.challenge.account.service.account.domain.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClienteResponse {
    private UUID id;
    private String nome;
    private String email;
    private String cpf;
}
