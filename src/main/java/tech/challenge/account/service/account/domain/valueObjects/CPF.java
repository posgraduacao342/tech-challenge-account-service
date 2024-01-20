package tech.challenge.account.service.account.domain.valueObjects;

import  tech.challenge.account.service.account.domain.exception.AtributoInvalidoException;

public class CPF extends ValueObject<String> {

    public CPF(String value) {
        super(value);
        validar(value);
    }

    @Override
    protected void validar(String cpf) {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        if (!cpf.matches("[0-9.-]+") || cpfLimpo.length() != 11) {
            throw new AtributoInvalidoException("O CPF deve conter apenas números, pontos e traços, e ter 11 dígitos.");
        }

        int primeiroDigitoVerificador = calcularDigito(cpfLimpo.substring(0, 9), 10);

        int segundoDigitoVerificador = calcularDigito(cpfLimpo.substring(0, 9) + primeiroDigitoVerificador, 11);

        if (Integer.parseInt(cpfLimpo.substring(9, 10)) != primeiroDigitoVerificador ||
                Integer.parseInt(cpfLimpo.substring(10, 11)) != segundoDigitoVerificador) {
            throw new AtributoInvalidoException("CPF inválido.");
        }
    }

    private int calcularDigito(String parteCpf, int pesoMaximo) {
        int soma = 0;
        for (int i = 0; i < parteCpf.length(); i++) {
            int digito = Integer.parseInt(parteCpf.substring(i, i + 1));
            soma += digito * pesoMaximo--;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }
}
