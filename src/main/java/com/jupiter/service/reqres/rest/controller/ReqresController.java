package com.jupiter.service.reqres.rest.controller;

import com.jupiter.service.reqres.dto.request.CreateUserRequestDTO;
import com.jupiter.service.reqres.dto.request.UserLoginRequestDTO;
import com.jupiter.service.reqres.dto.request.UserRegistrationRequestDTO;
import com.jupiter.service.reqres.rest.client.ReqresClient;
import com.jupiter.common.restclient.HttpResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReqresController {

    @Autowired
    private ReqresClient reqresClient;

    public HttpResponseWrapper sendUserLogin(UserLoginRequestDTO userLoginRequestDTO, int expectedCode) {
        return reqresClient
                .sendPostUserLoginRequest(userLoginRequestDTO)
                .expectStatusCode("Send user login request returned wrong status code ", expectedCode);
    }


    public HttpResponseWrapper getUsersList(int expectedCode) {
        return reqresClient
                .sendGetUsersListRequest()
                .expectStatusCode("Get users list request returned wrong status code ", expectedCode);
    }

    public HttpResponseWrapper sendUserRegistration(UserRegistrationRequestDTO userRegistrationRequestDTO, int expectedCode) {
        return reqresClient
                .sendPostUserRegistrationRequest(userRegistrationRequestDTO)
                .expectStatusCode("Send user registration request returned wrong status code ", expectedCode);
    }

    public HttpResponseWrapper getColorResources(int expectedCode) {
        return reqresClient
                .sendGetColorResourcesRequest()
                .expectStatusCode("Get resources request returned wrong status code ", expectedCode);
    }

    public HttpResponseWrapper createUser(CreateUserRequestDTO createUserRequestDTO, int expectedCode) {
        return reqresClient
                .sendPostCreateUserRequest(createUserRequestDTO)
                .expectStatusCode("Create user request returned wrong status code ", expectedCode);
    }
}
