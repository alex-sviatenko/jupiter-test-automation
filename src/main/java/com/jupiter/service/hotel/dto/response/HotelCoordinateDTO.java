package com.jupiter.service.hotel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelCoordinateDTO {
    private String id;
    private GeoDTO location;
    private Integer locationId;
    private String name;
}
