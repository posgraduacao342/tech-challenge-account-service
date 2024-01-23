package tech.challenge.account.service.account.helpers;

import tech.challenge.account.service.account.domain.dto.requets.CriarClienteRequest;

import java.util.Optional;

public class CadastrarClienteHelper {
    public static CriarClienteRequest gerarClienteRequest(Optional<String> cpf, Optional<String> email, Optional<String> nome) {
        var cliente = new CriarClienteRequest(
                nome.orElseGet(() -> buscarNomePadrao()),
                email.orElseGet(() -> buscarEmailPadrao()),
                cpf.orElseGet(() -> buscarCPFPadrao())
        );
        return cliente;
    }

    public static CriarClienteRequest gerarClienteRequest() {
        return gerarClienteRequest(Optional.empty(), Optional.empty(), Optional.empty());
    }

    private static String buscarCPFPadrao() {
        return "12345678909";
    }

    private static String buscarEmailPadrao() {
        return "email_padrao@test.com";
    }

    private static String buscarNomePadrao() {
        return "Matheus";
    }

}
