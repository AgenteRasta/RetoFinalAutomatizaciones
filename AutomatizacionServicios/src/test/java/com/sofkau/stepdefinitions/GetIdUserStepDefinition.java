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

import static com.sofkau.questions.ReturnApiJsonResponse.returnApiJsonResponse;
import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.RetoQAPIResources.RETOQ_API_URL;
import static com.sofkau.utils.RetoQAPIResources.USER_GET_ID_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetIdUserStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(GetIdUserStepDefinition.class));
    JSONObject resBody = null;
    JSONParser parser = new JSONParser();
    JSONArray jsonArray = null;
 
    @Given("que estoy en el API")
    public void que_estoy_en_el_API() {
        try {
            setUp(RETOQ_API_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }

    }

    @When("envio la peticion get con el {string} del usuario")
    public void envio_la_peticion_get_con_el_del_usuario(String id) {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(USER_GET_ID_RESOURCE.getValue() + id)

            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("recibo {int} de codigo de respuesta y la informacion del usuario {string}, {string} y {string}")
    public void recibo_de_codigo_de_respuesta_y_la_informacion_del_usuario_y(Integer statuscode, String nombre, String correo, String role) {
        try{
            Response actualResponse = (Response) returnApiJsonResponse().answeredBy(actor);
            resBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: "+ resBody,
                            responseCreate-> responseCreate.statusCode(statuscode)),
                    seeThat("Retorna informacion",
                            act-> actualResponse, notNullValue()),
                    seeThat("El id recibido es: ",
                            ids -> resBody.get("fullName").toString(), equalTo(nombre)),
                    seeThat("El id recibido es: ",
                            ids -> resBody.get("email").toString(), equalTo(correo)),
                    seeThat("El id recibido es: ",
                            ids -> resBody.get("role").toString(), equalTo(role))

            );
            LOGGER.info("Se finaliza el step de la pokeApi");
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    
    }
    @Given("que estoy en el API-Q")
    public void que_estoy_en_el_API_Q() {
        try {
            setUp(RETOQ_API_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

    @When("envio la peticion get con el {string} invalido")
    public void envio_la_peticion_get_con_el_invalido(String id) {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(USER_GET_ID_RESOURCE.getValue() + id)

            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("recibo {int} de codigo de respuesta")
    public void recibo_de_codigo_de_respuesta(Integer statuscode) {
        try{
            Response actualResponse = (Response) returnApiJsonResponse().answeredBy(actor);
            resBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
            actor.should(
                    seeThatResponse("El codigo de respuesta es: "+ resBody,
                            responseCreate-> responseCreate.statusCode(statuscode)),
                    seeThat("Retorna informacion",
                            act-> actualResponse, notNullValue())

            );
            LOGGER.info("Se finaliza el step de la API");
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }


}


