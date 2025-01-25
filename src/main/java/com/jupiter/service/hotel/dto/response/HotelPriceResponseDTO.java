package com.jupiter.service.hotel.dto.response;

import lombok.Data;

@Data
public class HotelPriceResponseDTO {
    private Integer locationId;
    private Integer hotelId;
    private Double priceFrom;
    private Double priceAvg;
    private PricePercentileDTO pricePercentile;
    private Integer stars;
    private String hotelName;
    private LocationDTO location;
}
