package tech.challenge.account.service.account.helpers;

import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.valueobjects.CPF;
import tech.challenge.account.service.account.domain.valueobjects.Email;

import java.util.Optional;
import java.util.UUID;

public class ClienteHelper {
    public static Cliente gerarCliente(Optional<CPF> cpf, Optional<Email> email, Optional<String> nome, Optional<UUID> id){
        return Cliente.builder()
                .cpf(cpf.orElseGet(() -> buscarCPFPadrao()))
                .email(email.orElseGet(() -> buscarEmailPadrao()))
                .nome(nome.orElseGet(() -> buscarNomePadrao()))
                .id(id.orElseGet(() -> buscarIdPadrao()))
                .build();
    }

    public static Cliente gerarCliente() {
        return gerarCliente(Optional.empty(), Optional.empty(), Optional.empty(),  Optional.empty());
    }

    private static CPF buscarCPFPadrao() {
        return new CPF("648.131.787-82");
    }

    private static Email buscarEmailPadrao() {
        return new Email("email_padrao@test.com");
    }

    private static UUID buscarIdPadrao() {
        return UUID.fromString("cc2f2531-76ac-4dce-a7ad-4beeacb46d50");
    }

    private static String buscarNomePadrao() {
        return "Matheus";
    }

    public static Cliente gerarClienteAdam() {
        return gerarCliente(
                Optional.of(new CPF("092.420.830-97")),
                Optional.of(new Email("Adam@gmail.com")),
                Optional.of("Adam"),
                Optional.of(UUID.fromString("5f789b39-4295-42c1-a65b-cfca5b987db2")));
    }
}
