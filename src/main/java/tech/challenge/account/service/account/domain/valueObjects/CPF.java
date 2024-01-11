package tech.challenge.account.service.account.domain.valueObjects;

import  tech.challenge.account.service.account.domain.exception.AtributoInvalidoException;
import lombok.Data;

import java.util.Objects;

@Data
public class CPF {

    private final String value;

    public CPF(String value) {
        validarCPF(value);
        this.value = value;
    }

    private void validarCPF(String cpf) {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        if (cpfLimpo.length() != 11) {
            throw new AtributoInvalidoException("O CPF deve conter 11 dígitos.");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf1 = (CPF) o;
        return value.equals(cpf1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
