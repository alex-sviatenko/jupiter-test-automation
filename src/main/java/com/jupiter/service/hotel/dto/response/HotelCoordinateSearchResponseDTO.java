package com.jupiter.service.hotel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelCoordinateSearchResponseDTO {
    private String status;
    private HotelCoordinateResultsDTO results;
}
