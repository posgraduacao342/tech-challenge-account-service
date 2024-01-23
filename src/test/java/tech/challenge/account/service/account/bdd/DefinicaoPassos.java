package tech.challenge.account.service.account.bdd;

import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tech.challenge.account.service.account.domain.dto.responses.ClienteResponse;
import tech.challenge.account.service.account.helpers.CadastrarClienteHelper;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class DefinicaoPassos {
    private Response response;
    private ClienteResponse clienteResposta;
    private String ENDPOINT_API_CLIENTE = "http://localhost:8080/clientes";


    @Quando("cadastro um cliente")
    public ClienteResponse registrar_um_novo_cliente() {
        var clienteRequest = CadastrarClienteHelper.gerarClienteRequest();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteRequest)
                .when()
                .post(ENDPOINT_API_CLIENTE);

        clienteResposta = response.then().extract().as(ClienteResponse.class);

        return response.then().extract().as(ClienteResponse.class);
    }

    @Entao("o cliente é registrado com sucesso")
    public void o_cliente_é_registrado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteSchema.json"));
    }

    @Entao("deve ser apresentado")
    public void deve_ser_apresentado() {
        response.then()
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteSchema.json"));

        removerCliente(clienteResposta.getId());
    }

    @Dado("que já possui cliente cadastrado")
    public void que_já_possui_clientes_cadastrado() {
        clienteResposta = registrar_um_novo_cliente();
    }
    @Quando("efetuar a busca por cpf")
    public void efetuar_a_busca_por_cpf() {
        response = when()
            .get(ENDPOINT_API_CLIENTE + "/{cpf}", clienteResposta.getCpf());
    }
    @Entao("cliente é retornado")
    public void cliente_e_retornado() {
        response.then()
                .body(matchesJsonSchemaInClasspath("./schemas/ClienteSchema.json"));

        removerCliente(clienteResposta.getId());
    }

    @Quando("requisitar a remoção do cliente")
    public void requisitar_a_remoção_do_cliente() {
        response = when()
                .delete(ENDPOINT_API_CLIENTE + "/{id}", clienteResposta.getId());
    }

    @Entao("o cliente é removido com sucesso")
    public void cliente_e_removido_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    private void removerCliente(UUID id) {
        when()
                .delete(ENDPOINT_API_CLIENTE + "/{id}", id)
                .then();
    }
}
