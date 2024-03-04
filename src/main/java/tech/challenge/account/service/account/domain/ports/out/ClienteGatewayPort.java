package tech.challenge.account.service.account.domain.ports.out;

import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.exception.RecursoNaoEncontratoException;
import tech.challenge.account.service.account.domain.valueobjects.CPF;
import tech.challenge.account.service.account.domain.valueobjects.Email;

import java.util.List;
import java.util.UUID;

public interface ClienteGatewayPort {
    List<Cliente> buscarClientes();
    Cliente cadastrarCliente(Cliente cliente);
    void deletarCliente(UUID id);
    boolean clienteExiste(CPF cpf,  Email email);
    Cliente buscarClientePorCpf(CPF cpf) throws RecursoNaoEncontratoException;
    Cliente buscarClientePorId(UUID id) throws RecursoNaoEncontratoException;
    Cliente buscarClientePorEmail(Email email) throws RecursoNaoEncontratoException;
}
