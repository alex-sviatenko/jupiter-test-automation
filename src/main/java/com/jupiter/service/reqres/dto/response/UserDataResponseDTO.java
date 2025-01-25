package com.jupiter.service.reqres.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDataResponseDTO {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
