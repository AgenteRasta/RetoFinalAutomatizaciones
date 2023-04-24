package com.sofkau.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class AbrirPaginaInicial implements Task {

    private String paginaInicial;
    public AbrirPaginaInicial conLaPagina(String paginaInicial){
        this.paginaInicial = paginaInicial;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(paginaInicial)
        );
    }
    public static AbrirPaginaInicial abrirPaginaInicial(){
        return new AbrirPaginaInicial();
    }
}
