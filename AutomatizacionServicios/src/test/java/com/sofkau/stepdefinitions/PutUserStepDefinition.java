package com.sofkau.stepdefinitions;

import com.sofkau.models.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.logging.Logger;

import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.tasks.DoPut.doPut;
import static com.sofkau.utils.RetoQAPIResources.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class PutUserStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(PutUserStepDefinition.class));
    User users = new User();
    @Given("que estoy en el API-Reto-Q")
    public void que_estoy_en_el_API_Reto_Q() {
        try {
            setUp(RETOQ_API_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

    @When("envio la peticion put con el {string} del usuario con los datos {string} {string} {string} {string} {string} {string}")
    public void envio_la_peticion_put_con_el_del_usuario_con_los_datos(String id,String fullName, String email, String level, String avaliable, String values, String role){
        try {
            users.setFullName(fullName);
            users.setEmail(email);
            users.setLevel(level);
            users.setAvailable(Boolean.valueOf(avaliable));
            users.setValues(values);
            users.setRole(role);

            actor.attemptsTo(
                    doPut()
                            .withTheResource(USER_PUT_RESOURCE.getValue() + id)
                            .andTheRequestBody(users)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("recibo {int} como respuesta")
    public void recibo_como_respuesta(Integer statuscode) {
        try {
            actor.should(
                    seeThatResponse("Se debe mostrar el usuario creado",
                            response -> response
                                    .statusCode(statuscode)
                                    .body("fullName", equalTo(users.getFullName()))
                                    .body("mail", equalTo(users.getEmail()))
                                    .body("level", equalTo(users.getLevel()))
                                    .body("available", equalTo(users.getAvailable()))
                                    .body("values", equalTo(users.getValues()))
                                    .body("role", equalTo(users.getRole()))
                    ));
            LOGGER.info("Se finaliza el step de la API");
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

}
