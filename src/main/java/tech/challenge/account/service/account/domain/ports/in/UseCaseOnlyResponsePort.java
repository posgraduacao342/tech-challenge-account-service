package tech.challenge.account.service.account.domain.ports.in;

public interface UseCaseOnlyResponsePort<T> {
    T execute();
}