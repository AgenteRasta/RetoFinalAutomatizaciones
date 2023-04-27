package com.sofkau.stepdefinitions;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.sofkau.tasks.DoGet.doGet;
import static com.sofkau.utils.ReqresResources.BASE_URL;
import static com.sofkau.utils.ReqresResources.RECURSO_VER_TODOS;

public class VerPreguntasStepDefinition extends ApiSetUp {

    @Given("que estoy apuntando con un endpoint al servicio rest")
    public void queEstoyApuntandoConUnEndpointAlServicioRest() {
        try{
            setUp(BASE_URL.getValue());
        }catch (Exception e){

        }
    }

    @When("envio la peticion get con el recurso del servicio")
    public void envioLaPeticionGetConElRecursoDelServicio() {
        try{
            actor.attemptsTo(
                    doGet()
                            .withTheResource(RECURSO_VER_TODOS.getValue())
            );
        }catch (Exception e){

        }

    }

    @Then("recibo todas las preguntas")
    public void reciboTodasLasPreguntas() {
        try{

        }catch (Exception e){

        }
    }
}
