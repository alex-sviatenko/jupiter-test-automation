package com.jupiter.service.hotel.dto.response;

import lombok.Data;

@Data
public class HotelDTO {
    private String id;
    private GeoDTO location;
    private Double _score;
    private String locationName;
    private Integer locationId;
    private String fullName;
    private String label;
}
