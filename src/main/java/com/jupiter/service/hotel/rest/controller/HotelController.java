package com.jupiter.service.hotel.rest.controller;

import com.jupiter.service.hotel.rest.client.HotelClient;
import com.jupiter.common.restclient.HttpResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class HotelController {

    @Autowired
    private HotelClient hotelClient;

    public HttpResponseWrapper getHotelsData(String hotelName, int expectedCode) {
        return hotelClient
                .sendGetHotelNameSearchRequest(hotelName)
                .expectStatusCode("Get hotel data by name request returned wrong status code ", expectedCode);
    }

    public HttpResponseWrapper getHotelsData(Double latitude, Double longitude, int expectedCode) {
        return hotelClient
                .sendGetHotelCoordinateSearchRequest(latitude, longitude)
                .expectStatusCode("Get hotel data by coordinates request returned wrong status code ", expectedCode);
    }

    public HttpResponseWrapper getHotelLivingCost(String locationName, LocalDate checkInDate, LocalDate checkOutDate, int expectedCode) {
        return hotelClient
                .sendGetHotelLivingCostRequest(locationName, checkInDate, checkOutDate)
                .expectStatusCode("Get hotel living cost request returned wrong status code ", expectedCode);
    }

    public HttpResponseWrapper getHotelLivingCost(Integer locationId, LocalDate checkInDate, LocalDate checkOutDate, int expectedCode) {
        return hotelClient
                .sendGetHotelLivingCostRequest(locationId, checkInDate, checkOutDate)
                .expectStatusCode("Get hotel living cost request returned wrong status code ", expectedCode);
    }
}
