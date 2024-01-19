package tech.challenge.account.service.account.domain.ports.in;

public interface UseCasePort<Response, Request> {
    Response execute(Request request);
}