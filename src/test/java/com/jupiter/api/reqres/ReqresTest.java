package com.jupiter.api.reqres;

import com.jupiter.api.BaseTest;
import com.jupiter.service.reqres.dto.response.colordata.ColorDataDTO;
import com.jupiter.service.reqres.dto.response.colordata.ColorResourceResponseDTO;
import com.jupiter.service.reqres.dto.request.CreateUserRequestDTO;
import com.jupiter.service.reqres.dto.request.UserLoginRequestDTO;
import com.jupiter.service.reqres.dto.request.UserRegistrationRequestDTO;
import com.jupiter.service.reqres.dto.response.*;
import com.jupiter.utils.DateTimeUtils;
import com.jupiter.common.restclient.HttpResponseWrapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.jupiter.utils.DateTimeUtils.DATE_TIME_SSS_FORMATTER;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

public class ReqresTest extends BaseTest {

    @Test
    public void userLoggedInSuccessfully() {
        int tokenLengthExpected = 17;
        UserLoginRequestDTO userLoginRequestDTO = new UserLoginRequestDTO("eve.holt@reqres.in", "cityslicka");
        UserLoginResponseDTO userLoginResponseDTO = reqresSteps.userLogin(userLoginRequestDTO);

        assertNotNull(userLoginResponseDTO.getToken());
        assertEquals(tokenLengthExpected, userLoginResponseDTO.getToken().length());
    }

    @Test
    public void userLoginWithoutPassword() {
        UserLoginRequestDTO userLoginRequestDTO = new UserLoginRequestDTO("peter@klaven", "");
        HttpResponseWrapper userLoginResponse = reqresController.sendUserLogin(userLoginRequestDTO, SC_BAD_REQUEST);

        assertEquals("Missing password", userLoginResponse.getBodyAsString("error"));
    }

    @Test
    public void checkUserAvatarAndEmail() {
        List<UserDataResponseDTO> usersList = reqresSteps.getUsersList();

        usersList.forEach(user -> assertTrue(user.getAvatar().contains(user.getId().toString())));

        assertTrue(usersList.stream().allMatch(user -> user.getEmail().endsWith("@reqres.in")));
    }

    @Test
    public void userRegisteredSuccessfully() {
        int idExpected = 4;
        int tokenLengthExpected = 17;

        UserRegistrationRequestDTO userRegistration = new UserRegistrationRequestDTO("eve.holt@reqres.in", "pistol");
        UserRegistrationResponseDTO userRegistrationResponseDTO = reqresSteps.createUserRegistration(userRegistration);

        assertEquals(idExpected, userRegistrationResponseDTO.getId());
        assertNotNull(userRegistrationResponseDTO.getToken());
        assertEquals(tokenLengthExpected, userRegistrationResponseDTO.getToken().length());
    }

    @Test
    public void userRegistrationWithoutPassword() {
        UserRegistrationRequestDTO userRegistration = new UserRegistrationRequestDTO("sydney@fife", "");
        HttpResponseWrapper userRegistrationResponse = reqresController.sendUserRegistration(userRegistration, SC_BAD_REQUEST);
        UserRegistrationUnsuccessfulResponseDTO userRegistrationUnsuccessfulResponse = userRegistrationResponse.getBodyAs(UserRegistrationUnsuccessfulResponseDTO.class);

        assertEquals("Missing password", userRegistrationUnsuccessfulResponse.getError());
    }

    @Test
    public void checkColorsSortedYears() {
        ColorResourceResponseDTO colorResourceResponseDTO = reqresSteps.getColorResources();

        List<Integer> yearsActual = colorResourceResponseDTO.getData().stream().map(ColorDataDTO::getYear).toList();
        List<Integer> sortedYearsExpected = yearsActual.stream().sorted().toList();

        assertEquals(sortedYearsExpected, yearsActual);
    }

    @Test
    public void checkColorElementsSizePerPage() {
        ColorResourceResponseDTO colorResourceResponseDTO = reqresSteps.getColorResources();
        int colorsSize = colorResourceResponseDTO.getData().size();

        assertEquals(colorResourceResponseDTO.getPer_page(), colorsSize);
    }

    @Test
    public void userCreatedSuccessfully() {
        String username = "morpheus";
        String job = "leader";
        int userIdMaxLengthExpected = 3;
        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO(username, job);

        CreateUserResponseDTO createUserResponseDTO = reqresSteps.createUser(createUserRequestDTO);

        assertEquals(username, createUserResponseDTO.getName());
        assertEquals(job, createUserResponseDTO.getJob());

        assertNotNull(createUserResponseDTO.getId());
        int userIdMaxLengthActual = createUserResponseDTO.getId().toString().length();
        assertTrue(userIdMaxLengthActual <= userIdMaxLengthExpected);

        LocalDateTime localDateTime = DateTimeUtils.parseDateTime(createUserResponseDTO.getCreatedAt(), DATE_TIME_SSS_FORMATTER);
        LocalDate localDateActual = localDateTime.toLocalDate();
        LocalDate localDateExpected = DateTimeUtils.getDateNow();

        assertEquals(localDateExpected, localDateActual);
    }
}
