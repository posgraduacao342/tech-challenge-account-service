package tech.challenge.account.service.account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CadastrarClienteDto {
    private String email;
    private String cpf;
    private String nome;
}
