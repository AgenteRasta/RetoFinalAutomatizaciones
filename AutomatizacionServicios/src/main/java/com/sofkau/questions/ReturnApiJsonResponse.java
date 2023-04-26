package com.sofkau.questions;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;


public class ReturnApiJsonResponse implements Question<Response> {


    @Override
    public Response answeredBy(Actor actor) {
        return (Response) SerenityRest.lastResponse().body();
    }

    public static ReturnApiJsonResponse returnApiJsonResponse(){
        return new ReturnApiJsonResponse();
    }
}
