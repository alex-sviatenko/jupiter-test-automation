package com.jupiter.service.hotel.dto.builder;

import com.jupiter.service.hotel.enums.Hotels;
import com.jupiter.service.hotel.dto.response.HotelCoordinateDTO;
import com.jupiter.service.hotel.dto.response.GeoDTO;

public class HotelCoordinateBuilder {

    public static HotelCoordinateDTO generateHotelCoordinateDTO(String id, Hotels hotel) {
        GeoDTO locationDTO = new GeoDTO(hotel.getLat(), hotel.getLon());

        HotelCoordinateDTO hotelCoordinateDTO = HotelCoordinateDTO.builder()
                .id(id)
                .location(locationDTO)
                .locationId(hotel.getLocationId())
                .name(hotel.getHotelName())
                .build();

        return hotelCoordinateDTO;
    }
}
