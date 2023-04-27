package com.sofkau.utils;

public enum ReqresResources {
    BASE_URL("https://reto-q-backend-production.up.railway.app/"),
    RECURSO_VER_TODOS("question/get-all"),
    RECURSO_CREAR_PREGUNTA("question/new"),
    RECURSO_ELIMINAR_PREGUNTA("question/delete/"),
    RECURSO_VERIFICAR_TEST("test/get/"),
    RECURSO_ACTUALIZAR_PREGUNTA("question/update/");



    private final String  value;

    ReqresResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
