package com.jupiter.service.hotel.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class HotelCoordinateResultsDTO {
    private List<HotelCoordinateDTO> hotels;
}
