package com.jupiter.service.reqres.dto.response;

import lombok.Data;

@Data
public class CreateUserResponseDTO {
    private String name;
    private String job;
    private Integer id;
    private String createdAt;
}
