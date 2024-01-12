package tech.challenge.account.service.account.domain.useCases;

import lombok.AllArgsConstructor;
import tech.challenge.account.service.account.domain.dto.CadastrarClienteDto;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.domain.exception.RecursoJaExisteException;
import tech.challenge.account.service.account.domain.ports.in.UseCasePort;
import tech.challenge.account.service.account.domain.ports.out.ClienteGatewayPort;
import tech.challenge.account.service.account.domain.valueObjects.CPF;
import tech.challenge.account.service.account.domain.valueObjects.Email;

@AllArgsConstructor
public class CadastrarClienteUseCase implements UseCasePort<Cliente, CadastrarClienteDto> {
    private final ClienteGatewayPort clienteGatewayPort;

    @Override
    public Cliente execute(CadastrarClienteDto dto) {
        var emailValido = new Email(dto.getEmail());
        var cpfValido = new CPF(dto.getCpf());
        var optionalCliente = this.clienteGatewayPort.clienteExiste(cpfValido, emailValido);

        if(optionalCliente) {
            throw  new RecursoJaExisteException("Email ou Cpf já possui cadastro");
        }

        var cliente = new Cliente();
        cliente.setEmail(emailValido);
        cliente.setCpf(cpfValido);
        cliente.setNome(dto.getNome());

        return this.clienteGatewayPort.cadastrarCliente(cliente);
    }
}
