package tech.challenge.account.service.account.domain.ports.in;

public interface UseCasePort<T1, T2> {
    T1 execute(T2 request);
}