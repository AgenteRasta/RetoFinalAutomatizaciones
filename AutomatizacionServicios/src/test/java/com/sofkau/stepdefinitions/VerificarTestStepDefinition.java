package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import com.sofkau.utils.Funciones;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.util.logging.Logger;

import static com.sofkau.questions.ReturnApiJsonResponse.returnApiJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import static org.hamcrest.CoreMatchers.equalTo;
public class VerificarTestStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(CrearPreguntaStepDefinition.class));
    int nivelUsuario;
    JSONObject preguntaJson = null;
    JSONObject resBody = null;
    List<String> opciones;
    Response actualResponse;
    JSONParser parser = new JSONParser();
    List<String> lines;
    JSONArray jsonArray = null;
    @Given("que estoy apuntando con un endpoint de test a la api")
    public void queEstoyApuntandoConUnEndpointDeTestALaApi() {
        try{
            LOGGER.info("Se inicia la automatizacion de verificar test");
            setUp(BASE_URL.getValue());
        }catch (Exception e){
            LOGGER.info("Fallo al pasar la base url de los servicios");
            Assertions.fail();
        }
    }

    @When("cuando envio el {string} que corrsponde a un usuario de {int}")
    public void cuandoEnvioElQueCorrspondeAUnUsuarioDe(String token, Integer nivel) {
        try{
            nivelUsuario=nivel;
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RECURSO_VERIFICAR_TEST.getValue()+token)
            );
        }catch (Exception e){
            Assertions.fail();
        }
    }

    @Then("recibo el {int} de codigo de respuesta")
    public void reciboElDeCodigoDeRespuesta(Integer codigo) {
        try {
            LOGGER.info("Inician los asserts");
            actualResponse=returnApiJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: "+ actualResponse.getStatusCode(),
                            code -> code.statusCode(codigo))
            );
        }catch (Exception e){
            LOGGER.info("Fallo comparando los codigos de respuesta");
            Assertions.fail();
        }
    }

    @Then("la informacion de la pregunta creada o un mensaje de error")
    public void laInformacionDeLaPreguntaCreadaOUnMensajeDeError() {
        if(actualResponse.getStatusCode()==200) {
            try {
                resBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
                JSONArray qs = (JSONArray) resBody.get("questions");

                actor.should(
                        seeThat("El numero de preguntas son",
                                preguntas -> qs.size(), equalTo(15)),
                        seeThat("El nivel de las pregunta es",
                                niv -> resBody.get("level").toString(), equalTo(String.valueOf(nivelUsuario)))
                );

            } catch (Exception e) {
                Assertions.fail();
            }
        }else if(actualResponse.getStatusCode()==404){
            try {
                resBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
                String message=resBody.get("error").toString();

                actor.should(
                        seeThat("Se recibe un mensaje",
                                preguntas -> message, equalTo("Not Found"))
                );

            } catch (Exception e) {
                Assertions.fail();
            }
        }
    }
}
