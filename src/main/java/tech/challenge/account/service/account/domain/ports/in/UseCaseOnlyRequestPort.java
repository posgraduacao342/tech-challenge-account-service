package tech.challenge.account.service.account.domain.ports.in;

public interface UseCaseOnlyRequestPort<T> {
    void execute(T request);
}