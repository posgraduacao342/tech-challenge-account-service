package tech.challenge.account.service.account.domain.ports.in;

//import java.util.Optional;

public interface UseCasePort<Response, Params> {
    Response execute(Params params);
}