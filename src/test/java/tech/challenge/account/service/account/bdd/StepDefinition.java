package tech.challenge.account.service.account.bdd;

import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tech.challenge.account.service.account.domain.entities.Cliente;
import tech.challenge.account.service.account.helpers.ClienteHelper;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

class StepDefinition {

    private Response response;
    private Cliente clienteResposta;
    private String ENDPOINT_API_CLIENTE = "http://localhost:8080/clientes";

    @Quando("cadastro um cliente")
    public void registrar_um_novo_cliente() {
        var clienteRequest = ClienteHelper.gerarCliente();
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(clienteRequest)
                .when()
                .post(ENDPOINT_API_CLIENTE);
    }

    @Entao("o cliente é registrado com sucesso")
    public void o_cliente_é_registrado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.CREATED.value())
                .body(matchesJsonSchemaInClasspath("schemas/cliente.schema.json"));
    }

    @Entao("deve ser apresentado")
    public void deve_ser_apresentado() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/cliente.schema.json"));
    }
}
