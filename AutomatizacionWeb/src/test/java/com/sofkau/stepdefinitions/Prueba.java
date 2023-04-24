package com.sofkau.stepdefinitions;

import com.sofkau.setup.SetUpWeb;
import com.sofkau.tasks.AbrirPaginaInicial;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.sofkau.tasks.AbrirPaginaInicial.abrirPaginaInicial;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Prueba extends SetUpWeb {

    @Given("que el usuario esta en la pagina principal")
    public void queElUsuarioEstaEnLaPaginaPrincipal() {
        try {
            configurarNavegador();
            theActorInTheSpotlight().wasAbleTo(
                    abrirPaginaInicial()
                            .conLaPagina("https://www.demoblaze.com/index.html")
            );
        }catch (Exception e){
            //quitarDriver();
        }
    }

    @When("el usuario inicia sesion y presiona el boton logout")
    public void elUsuarioIniciaSesionYPresionaElBotonLogout() {

    }

    @Then("vuelve a la pagina principal")
    public void vuelveALaPaginaPrincipal() {

    }
}
