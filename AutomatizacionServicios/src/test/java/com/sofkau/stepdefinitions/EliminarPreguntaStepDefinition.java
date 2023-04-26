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

import static com.sofkau.questions.ReturnJsonResponse.returnJsonResponse;
import static com.sofkau.tasks.DoDelete.doDelete;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class EliminarPreguntaStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(CrearPreguntaStepDefinition.class));
    int codigoRespuesta;
    JSONObject preguntaJson = null;
    JSONObject resBody = null;
    List<String> opciones;
    Response actualResponse;
    JSONParser parser = new JSONParser();
    List<String> lines;
    JSONArray jsonArray = null;
    @Given("que estoy apuntando con un endpoint a la apirest")
    public void queEstoyApuntandoConUnEndpointALaApirest() {
        try{
            LOGGER.info("Se inicia la automatizacion de las preguntas");
            setUp(BASE_URL.getValue());
        }catch (Exception e){
            LOGGER.info("Fallo al pasar la base url de los servicios");
            Assertions.fail();
        }
    }

    @When("envio la peticion delete con el id de la pregunta mediante su {int}")
    public void envioLaPeticionDeleteConElIdDeLaPreguntaMedianteSu(Integer index) {
        try{
            LOGGER.info("");
            lines= Funciones.readTextFile("datosPruebasPreguntas.txt");
            String jsonString=lines.get(index);
            preguntaJson=(JSONObject) parser.parse(jsonString);
            actor.attemptsTo(
                    doDelete()
                            .withTheResource(RECURSO_ELIMINAR_PREGUNTA.getValue()+preguntaJson.get("_id"))
            );
        }catch (Exception e){
            Assertions.fail();
        }
    }

    @Then("recibo un {int} de respuesta y elimino la pregunta")
    public void reciboUnDeRespuestaYEliminoLaPregunta(Integer code) {
        try{
            actualResponse=returnJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: "+ actualResponse.getStatusCode(),
                            codigo -> codigo.statusCode(code))
            );
        }catch (Exception e){
            Assertions.fail();
        }
    }
}
