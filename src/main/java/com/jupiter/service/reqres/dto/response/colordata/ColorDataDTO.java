package com.jupiter.service.reqres.dto.response.colordata;

import lombok.Data;

@Data
public class ColorDataDTO {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;
}
