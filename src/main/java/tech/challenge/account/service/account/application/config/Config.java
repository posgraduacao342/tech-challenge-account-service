package tech.challenge.account.service.account.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.challenge.account.service.account.application.gateway.ClienteGateway;
import tech.challenge.account.service.account.domain.useCases.BuscarClientePorCPFUseCase;
import tech.challenge.account.service.account.domain.useCases.BuscarClientesUseCase;

@Configuration
public class Config {
    @Bean
    public BuscarClientesUseCase buscarClientesUseCaseConfig(ClienteGateway clienteGateway) {
        return new BuscarClientesUseCase(clienteGateway);
    }
    @Bean
    public BuscarClientePorCPFUseCase buscarClientePorCpfUseCaseConfig(ClienteGateway clienteGateway) {
        return new BuscarClientePorCPFUseCase(clienteGateway);
    }
}
