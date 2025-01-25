package com.jupiter.service.hotel.rest.client;

import com.jupiter.common.annotation.RestClient;
import com.jupiter.common.api.ApiClient;
import com.jupiter.common.factory.RestClientFactory;
import com.jupiter.common.restclient.HttpResponseWrapper;
import com.jupiter.common.restclient.RestClientWrapper;

import java.time.LocalDate;

import static java.lang.String.format;

@RestClient
public class HotelClient implements ApiClient {

    public static final String API_V2_HOTEL_SEARCH_NAME_LOCATION = "/api/v2/lookup.json";
    public static final String API_V2_HOTEL_LIVING_COST = "/api/v2/cache.json";

    // TODO: add base URI to environment config
    @Override
    public RestClientWrapper getClient() {
        return RestClientFactory.getClient("http://engine.hotellook.com");
    }

    public HttpResponseWrapper sendGetHotelNameSearchRequest(String hotelName) {
        return getClient()
                .queryParam("query", hotelName)
                .queryParam("lookFor", "hotel")
                .get(API_V2_HOTEL_SEARCH_NAME_LOCATION);
    }

    public HttpResponseWrapper sendGetHotelCoordinateSearchRequest(Double latitude, Double longitude) {
        return getClient()
                .queryParam("query", format("%s,%s", latitude, longitude))
                .queryParam("lookFor", "hotel")
                .queryParam("limit", "1")
                .get(API_V2_HOTEL_SEARCH_NAME_LOCATION);
    }

    public HttpResponseWrapper sendGetHotelLivingCostRequest(String locationName, LocalDate checkInDate, LocalDate checkOutDate) {
        return getClient()
                .queryParam("location", locationName)
                .queryParam("checkIn", checkInDate.toString())
                .queryParam("checkOut", checkOutDate.toString())
                .get(API_V2_HOTEL_LIVING_COST);
    }

    public HttpResponseWrapper sendGetHotelLivingCostRequest(Integer locationId, LocalDate checkInDate, LocalDate checkOutDate) {
        return getClient()
                .queryParam("locationId", locationId)
                .queryParam("checkIn", checkInDate.toString())
                .queryParam("checkOut", checkOutDate.toString())
                .get(API_V2_HOTEL_LIVING_COST);
    }
}
