package com.jupiter.service.hotel.dto.response;

import lombok.Data;

@Data
public class HotelSearchErrorResponseDTO {
    private String status;
    private Integer errorCode;
    private String message;
}
