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
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;
import java.util.List;
import java.util.logging.Logger;

import static com.sofkau.questions.ReturnJsonResponse.returnJsonResponse;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
public class CrearPreguntaStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(CrearPreguntaStepDefinition.class));

    int codigoRespuesta;
    JSONObject preguntaJson = null;
    JSONObject resBody = null;
    List<String> opciones;
    Response actualResponse;
    JSONParser parser = new JSONParser();
    List<String> lines;
    JSONArray jsonArray = null;
    @Given("que estoy apuntando con un endpoint a la api")
    public void queEstoyApuntandoConUnEndpointALaApi() {
        try{
            LOGGER.info("Se inicia la automatizacion de las preguntas");
            setUp(BASE_URL.getValue());
        }catch (Exception e){
            LOGGER.info("Fallo al pasar la base url de los servicios");
            Assertions.fail();
        }

    }

    @When("cuando envio la informacion de una pregunta mediante el {int}")
    public void cuandoEnvioLaInformacionDeUnaPreguntaMedianteEl(Integer index) throws ParseException {
        try{
            LOGGER.info("Se selecciona el json que sera enviado para probar y se envia el recurso de la url");
            lines=Funciones.readTextFile("datosPruebasPreguntas.txt");
            String jsonString=lines.get(index);
            preguntaJson=(JSONObject) parser.parse(jsonString);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RECURSO_CREAR_PREGUNTA.getValue())
                            .andTheRequestBody(preguntaJson)
            );
        }catch (Exception e){
            Assertions.fail();
        }


    }

    @Then("recibo {int} de codigo de respuesta")
    public void reciboDeCodigoDeRespuesta(Integer codigo) {
        try {
            LOGGER.info("Inician los asserts");
            codigoRespuesta=codigo;
            actualResponse=returnJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El codigo de respuesta es: "+ actualResponse.getStatusCode(),
                            code -> code.statusCode(codigo))
            );
        }catch (Exception e){
            LOGGER.info("Fallo comparando los codigos de respuesta");
            Assertions.fail();
        }

    }

    @Then("la informacion de la pregunta creada")
    public void laInformacionDeLaPreguntaCreada() throws ParseException {
        resBody=(JSONObject) parser.parse(actualResponse.getBody().asString());
        JSONArray opc=(JSONArray) resBody.get("options");
        JSONArray ans=(JSONArray) resBody.get("answer");
        if(actualResponse.getStatusCode()==201){
            if(preguntaJson.get("type").toString().equals("multiple")){
                try{
                    actor.should(
                            seeThat("Retorna la informacion",
                                    info-> actualResponse,notNullValue()),
                            seeThat("El tipo de pregunta creado es",
                                    type-> resBody.get("type").toString(),equalTo(preguntaJson.get("type").toString())),
                            seeThat("La cantidad de opciones son",
                                    opt-> opc.size(),equalTo(4)),
                            (Consequence) seeThat("La cantidad de respuestas es mayor a 1",
                                    answ1 -> ans.size(), greaterThan(1)),
                            (Consequence) seeThat("La cantidad de respuestas es menor a 5",
                                    answ5 -> ans.size(), lessThan(5))
                    );
                }catch (Exception e){
                    Assertions.fail();
                }
            }else if(preguntaJson.get("type").toString().equals("truefalse")){
                try{
                    actor.should(
                            seeThat("Retorna la informacion",
                                    info-> actualResponse,notNullValue()),
                            seeThat("El tipo de pregunta creado es",
                                    type-> resBody.get("type").toString(),equalTo(preguntaJson.get("type").toString())),
                            seeThat("La cantidad de opciones son",
                                    opt-> opc.size(),equalTo(2)),
                            seeThat("La cantidad de respuesta es igual a 1",
                                    answ-> ans.size(), equalTo(1))
                    );
                }catch (Exception e){
                    Assertions.fail();
                }
            }else{
                try{
                    actor.should(
                            seeThat("Retorna la informacion",
                                    info-> actualResponse,notNullValue()),
                            seeThat("El tipo de pregunta creado es",
                                    type-> resBody.get("type").toString(),equalTo(preguntaJson.get("type").toString())),
                            seeThat("La cantidad de opciones son",
                                    opt-> opc.size(),equalTo(4)),
                            seeThat("La cantidad de respuesta es igual a 1",
                                    answ-> ans.size(), equalTo(1))
                    );
                }catch (Exception e){

                }
            }
        }else if(codigoRespuesta==400){
            try {
                LOGGER.info(actualResponse.toString());
            }catch (Exception e){
                Assertions.fail();
            }
        }

        LOGGER.info("Fin de la automatizacion hecha para probar el post de las  preguntas");

    }
}
