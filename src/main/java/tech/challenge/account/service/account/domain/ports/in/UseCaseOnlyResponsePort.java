package tech.challenge.account.service.account.domain.ports.in;

public interface UseCaseOnlyResponsePort<Response> {
    Response execute();
}