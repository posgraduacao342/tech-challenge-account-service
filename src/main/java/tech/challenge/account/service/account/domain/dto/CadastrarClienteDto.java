package tech.challenge.account.service.account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CadastrarClienteDto {
    private String email;
    private String cpf;
    private String nome;
}
