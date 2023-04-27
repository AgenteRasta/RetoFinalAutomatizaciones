package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import com.sofkau.utils.Funciones;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Consequence;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.logging.Logger;

import static com.sofkau.questions.ReturnApiJsonResponse.returnApiJsonResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class ActualizarPreguntaStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(CrearPreguntaStepDefinition.class));

    int codigoRespuesta;
    JSONObject atributoAc = null;
    JSONObject resBody = null;
    List<String> opciones;
    Response actualResponse;
    JSONParser parser = new JSONParser();
    List<String> lines;
    JSONArray jsonArray = null;
    @Given("que estoy apuntando con un endpoint a la api para actualizar preguntas")
    public void queEstoyApuntandoConUnEndpointALaApiParaActualizarPreguntas() {
        try{
            LOGGER.info("Se inicia la automatizacion de las preguntas");
            setUp(BASE_URL.getValue());
        }catch (Exception e){
            LOGGER.info("Fallo al pasar la base url de los servicios");
            Assertions.fail();
        }
    }

    @When("cuando envio el recurso con {string} de la pregunta que deseo actualizar y la informacion mediante el {int}")
    public void cuandoEnvioElRecursoConDeLaPreguntaQueDeseoActualizarYLaInformacionMedianteEl(String id, Integer index) {
        try{
            lines= Funciones.readTextFile("datosPruebasPut.txt");
            String jsonString=lines.get(index);
            atributoAc=(JSONObject) parser.parse(jsonString);
            actor.attemptsTo(
                    doPut()
                            .withTheResource(RECURSO_ACTUALIZAR_PREGUNTA.getValue()+id)
                            .andTheRequestBody(atributoAc)
            );
        }catch (Exception e){
            Assertions.fail();
        }
    }

    @Then("recibo un {int} de codigo de respuesta")
    public void reciboUnDeCodigoDeRespuesta(Integer codigo) {
        try {
            LOGGER.info("Inician los asserts");
            codigoRespuesta=codigo;
            actualResponse=returnApiJsonResponse().answeredBy(actor);
            actor.should(
                    seeThat("El codigo de respuesta es: ",
                            code -> actualResponse.getStatusCode(), equalTo(codigo))
            );
        }catch (Exception e){
            LOGGER.info("Fallo comparando los codigos de respuesta");
            Assertions.fail();
        }

    }

    @Then("verifico la informacion actualizada {string}")
    public void verificoLaInformacionActualizada(String atributo) throws ParseException {
        resBody = (JSONObject) parser.parse(actualResponse.getBody().asString());

        if (actualResponse.getStatusCode() == 200) {
            try {
                String actualizado = resBody.get(atributo).toString();
                String enviado = atributoAc.get(atributo).toString();
                actor.should(
                        seeThat("La informacion actualizada es",
                                act -> actualizado, equalTo(enviado))
                );
            } catch (Exception e) {
                Assertions.fail();
            }
        }
        else if(actualResponse.getStatusCode()==400){
            try {
                resBody = (JSONObject) parser.parse(actualResponse.getBody().asString());
                String message=resBody.get("error").toString();

                actor.should(
                        seeThat("Se recibe un mensaje",
                                preguntas -> message, equalTo("Bad Request"))
                );

            } catch (Exception e) {
                Assertions.fail();
            }
        }
    }
}
