package tech.challenge.account.service.account.domain.ports.in;

public interface UseCaseOnlyRequestPort<Request> {
    void execute(Request request);
}