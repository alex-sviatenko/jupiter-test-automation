package com.jupiter.service.hotel.dto.response;

import lombok.Data;

@Data
public class LocationDTO {
    public String name;
    public String country;
    public Object state;
    public GeoDTO geo;
}
