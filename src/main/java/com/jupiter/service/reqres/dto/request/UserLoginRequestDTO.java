package com.jupiter.service.reqres.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequestDTO {
    private String email;
    private String password;
}
