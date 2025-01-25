package com.jupiter.service.hotel.dto.response;

import lombok.Data;

@Data
public class HotelSearchResponseDTO {
    private String status;
    private ResultsDTO results;
}
