package com.jupiter.service.reqres.rest.client;

import com.jupiter.common.annotation.RestClient;
import com.jupiter.common.api.ApiClient;
import com.jupiter.common.factory.RestClientFactory;
import com.jupiter.common.restclient.HttpResponseWrapper;
import com.jupiter.common.restclient.RestClientWrapper;

@RestClient
public class ReqresClient implements ApiClient {

    private static final String API_LOGIN = "/api/login";
    private static final String API_LIST_USERS = "/api/users";
    private static final String API_REGISTER_SUCCESSFUL = "/api/register";
    private static final String API_RESOURCE = "/api/resources";
    private static final String API_USERS = "/api/users";

    // TODO: add base URI to environment config
    @Override
    public RestClientWrapper getClient() {
        return RestClientFactory.getClient("https://reqres.in");
    }

    public HttpResponseWrapper sendPostUserLoginRequest(Object body) {
        return getClient()
                .body(body)
                .post(API_LOGIN);
    }

    public HttpResponseWrapper sendGetUsersListRequest() {
        return getClient()
                .get(API_LIST_USERS);
    }

    public HttpResponseWrapper sendPostUserRegistrationRequest(Object body) {
        return getClient()
                .body(body)
                .post(API_REGISTER_SUCCESSFUL);
    }

    public HttpResponseWrapper sendGetColorResourcesRequest() {
        return getClient()
                .get(API_RESOURCE);
    }

    public HttpResponseWrapper sendPostCreateUserRequest(Object body) {
        return getClient()
                .body(body)
                .post(API_USERS);
    }
}
