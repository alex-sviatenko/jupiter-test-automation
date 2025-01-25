package com.jupiter.common.restclient;

import com.jupiter.common.exception.TAFRuntimeException;
import io.restassured.response.ValidatableResponse;
import lombok.SneakyThrows;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;

public record HttpResponseWrapper(ValidatableResponse response) {

    private static final String DEFAULT_STATUS_CODE_ERROR = "\nExpecting %s status code but was %s!";

    public HttpResponseWrapper expectStatusCode(String errorMessage, int statusCode) {
        validateResponseCode(errorMessage, statusCode);

        return this;
    }

    public HttpResponseWrapper statusCode(int statusCode) {
        response.statusCode(statusCode);

        return this;
    }

    public String getBodyAsString() {
        return response.extract().body().asString();
    }

    public String getBodyAsString(String jsonPath) {
        return response.extract().jsonPath().getString(jsonPath);
    }

    public int getBodyAsInteger() {
        return response.extract().body().as(Integer.class);
    }

    public boolean getBodyAsBoolean() {
        return response.extract().body().as(Boolean.class);
    }

    public boolean getBodyAsBoolean(String jsonPath) {
        return response.extract().jsonPath().getBoolean(jsonPath);
    }

    public String getBodyAsPrettyString() {
        return response.extract().asPrettyString();
    }

    public <T> T getBodyAs(Class<T> returnType) {
        return response.extract().body().as(returnType);
    }

    public <T> List<T> getBodyAsList(Class<T> returnType) {
        return response.extract().jsonPath().getList(".", returnType);
    }

    public <T> List<T> getBodyAsList(String jsonPath, Class<T> returnType) {
        return response.extract().jsonPath().getList(jsonPath, returnType);
    }

    public int getResponseCode() {
        return response.extract().statusCode();
    }

    public String getHeader(String header) {
        return response.extract().header(header);
    }

    @SneakyThrows
    private void validateResponseCode(String errorMessage, int expectedStatusCode) {
        try {
            response.statusCode(equalTo(expectedStatusCode));
        } catch (AssertionError error) {
            throw new TAFRuntimeException(errorMessage + format(DEFAULT_STATUS_CODE_ERROR, expectedStatusCode, getResponseCode()), error);
        }
    }
}