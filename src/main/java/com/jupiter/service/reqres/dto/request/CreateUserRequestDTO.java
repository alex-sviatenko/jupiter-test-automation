package com.jupiter.service.reqres.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserRequestDTO {
    private String name;
    private String job;
}
