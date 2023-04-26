package com.sofkau.utils;

public enum RetoQAPIResources {
    RETOQ_API_URL("https://reto-q-backend-production.up.railway.app/"),
    USER_GET_ID_RESOURCE("user/getUser/"),
    USER_POST_RESOURCE("user/registerUser"),
    USER_PUT_RESOURCE("user/updateUser/");

    private final String  value;

    RetoQAPIResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
