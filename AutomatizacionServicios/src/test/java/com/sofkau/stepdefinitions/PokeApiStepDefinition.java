package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;

import java.util.logging.Logger;

import static com.sofkau.questions.ReturnPokeApiJsonResponse.returnPokeApiJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


public class PokeApiStepDefinition extends ApiSetUp {
    public static String idJ;
    public static Logger LOGGER = Logger.getLogger(String.valueOf(PokeApiStepDefinition.class));
    JSONObject resBody = null;
    JSONParser parser = new JSONParser();
    JSONArray jsonArray = null;
    private String BASE_URL="https://pokeapi.co/api/v2/";
    private String VER_JUEGO_POKEMON="version/";
    @Given("que estoy apuntando con un endpoint a la pokeapi")
    public void queEstoyApuntandoConUnEndpointALaPokeapi() {
        setUp(BASE_URL);
        LOGGER.info("Inicio de la automatizacion");
    }

    @When("envio la peticion get con el {string} del juego")
    public void envioLaPeticionGetConElDelJuego(String id) {
        try {

            idJ=id;
            actor.attemptsTo(
                    doGet()
                            .withTheResource(VER_JUEGO_POKEMON+id)
            );
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
        }

    }

    @Then("recibo {int} de codigo de respuesta y la informacion del juego")
    public void reciboDeCodigoDeRespuestaYLaInformacionDelJuego(Integer codigo) {
        try{
            Response actualResponse = (Response) returnPokeApiJsonResponse().answeredBy(actor);
            resBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: "+ resBody,
                            responseCreate-> responseCreate.statusCode(codigo)),
                    seeThat("Retorna informacion",
                            act-> actualResponse, notNullValue()),
                    seeThat("El id recibido es: ",
                            ids -> resBody.get("id").toString(), equalTo(idJ))

            );
            LOGGER.info("Se finaliza el step de la pokeApi");
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }

        //ResponsePokeApi actualResponsePokeApi=returnPokeApiJsonResponse().answeredBy(actor);

    }
}
