package com.sofkau.stepdefinitions;

import com.sofkau.models.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import java.util.logging.Logger;
import static com.sofkau.tasks.DoPost.doPost;
import static com.sofkau.utils.RetoQAPIResources.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostUserStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(PostUserStepDefinition.class));
    User users = new User();
    @Given("que estoy en el API-reto-Q")
    public void que_estoy_en_el_API_reto_Q() {
        try {
            setUp(RETOQ_API_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }
    @When("envio la peticion Post con los siguientes datos {string} {string} {string} {string} {string} {string} del usuario")
    public void envio_la_peticion_Post_con_los_siguientes_datos_del_usuario(String fullName, String email, String level, String avaliable, String values, String role) {

        try {
            users.setFullName(fullName);
            users.setEmail(email);
            users.setLevel(level);
            users.setAvailable(Boolean.valueOf(avaliable));
            users.setValues(values);
            users.setRole(role);

            actor.attemptsTo(
                    doPost()
                            .withTheResource(USER_POST_RESOURCE.getValue())
                            .andTheRequestBody(users)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("recibo {int} y la informacion del usuario creado")
    public void recibo_y_la_informacion_del_usuario_creado(Integer statuscode) {
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

    @Given("que estoy en el API-reto")
    public void que_estoy_en_el_API_reto() {
        try {
            setUp(RETOQ_API_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

    @When("envio la peticion Post con los datos {string} {string} {string} {string} {string} {string} del usuario")
    public void envio_la_peticion_Post_con_los_datos_del_usuario(String fullname, String email, String level, String avaliable, String values, String role){
        users.setFullName(fullname);
        users.setEmail(email);
        users.setLevel(level);
        users.setAvailable(Boolean.valueOf(avaliable));
        users.setValues(values);
        users.setRole(role);
        try {
            actor.attemptsTo(
                    doPost()
                            .withTheResource(USER_POST_RESOURCE.getValue())
                            .andTheRequestBody(users)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("recibo {int}")
    public void recibo(Integer statuscode) {
        try {
            actor.should(
                    seeThatResponse("Se debe mostrar el libro creado",
                            response -> response
                                    .statusCode(statuscode)
                    ));
            LOGGER.info("Se finaliza el step de la API");
        }catch (Exception e){
            LOGGER.warning(e.getMessage());
            Assertions.fail();
        }
    }


}
