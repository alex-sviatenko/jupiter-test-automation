package com.jupiter.service.reqres.steps;

import com.jupiter.common.annotation.Steps;
import com.jupiter.service.reqres.dto.response.colordata.ColorResourceResponseDTO;
import com.jupiter.service.reqres.dto.request.CreateUserRequestDTO;
import com.jupiter.service.reqres.dto.request.UserLoginRequestDTO;
import com.jupiter.service.reqres.dto.request.UserRegistrationRequestDTO;
import com.jupiter.service.reqres.dto.response.CreateUserResponseDTO;
import com.jupiter.service.reqres.dto.response.UserDataResponseDTO;
import com.jupiter.service.reqres.dto.response.UserLoginResponseDTO;
import com.jupiter.service.reqres.dto.response.UserRegistrationResponseDTO;
import com.jupiter.service.reqres.rest.controller.ReqresController;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

@Steps
@Log4j2
public class ReqresSteps {

    @Autowired
    private ReqresController reqresController;

    @Step("Make a user login")
    public UserLoginResponseDTO userLogin(UserLoginRequestDTO userLoginRequestDTO) {
        log.info("User login request");

        return reqresController
                .sendUserLogin(userLoginRequestDTO, SC_OK)
                .getBodyAs(UserLoginResponseDTO.class);
    }

    @Step("Get the list of users")
    public List<UserDataResponseDTO> getUsersList() {
        log.info("Get user list request");

        return reqresController
                .getUsersList(SC_OK)
                .getBodyAsList("data", UserDataResponseDTO.class);
    }

    @Step("Create user account registration")
    public UserRegistrationResponseDTO createUserRegistration(UserRegistrationRequestDTO userRegistrationRequestDTO) {
        log.info("Create user registration with email={}", userRegistrationRequestDTO.getEmail());

        return reqresController
                .sendUserRegistration(userRegistrationRequestDTO, SC_OK)
                .getBodyAs(UserRegistrationResponseDTO.class);
    }

    @Step("Get color resources")
    public ColorResourceResponseDTO getColorResources() {
        log.info("Get color resources request");

        return reqresController
                .getColorResources(SC_OK)
                .getBodyAs(ColorResourceResponseDTO.class);
    }

    @Step("Create user account")
    public CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO) {
        log.info("Create new user with name={}, job={}", createUserRequestDTO.getName(), createUserRequestDTO.getJob());

        return reqresController
                .createUser(createUserRequestDTO, SC_CREATED)
                .getBodyAs(CreateUserResponseDTO.class);
    }
}
