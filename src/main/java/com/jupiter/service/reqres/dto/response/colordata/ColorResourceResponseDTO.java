package com.jupiter.service.reqres.dto.response.colordata;

import lombok.Data;

import java.util.List;

@Data
public class ColorResourceResponseDTO {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<ColorDataDTO> data;
    private SupportDTO support;
}
