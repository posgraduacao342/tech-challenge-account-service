package tech.challenge.account.service.account.helpers;

import tech.challenge.account.service.account.infrastructure.db.entities.ClienteEntity;

import java.util.Optional;

public class ClienteEntityHelper {
    public static ClienteEntity gerarClienteEntity(Optional<String> cpf, Optional<String> email, Optional<String> nome){
        return ClienteEntity.builder()
                .cpf(cpf.orElseGet(() -> buscarCPFPadrao()))
                .email(email.orElseGet(() -> buscarEmailPadrao()))
                .nome(nome.orElseGet(() -> buscarNomePadrao()))
                .build();
    }

    public static ClienteEntity gerarClienteEntity() {
        return gerarClienteEntity(Optional.empty(), Optional.empty(), Optional.empty());
    }

    private static String buscarCPFPadrao() {
        return "123.456.789-09";
    }

    private static String buscarEmailPadrao() {
        return "email_padrao@test.com";
    }

    private static String buscarNomePadrao() {
        return "Matheus";
    }

}
