package com.jupiter.api.hotel;

import com.jupiter.api.BaseTest;
import com.jupiter.service.hotel.dto.response.HotelSearchErrorResponseDTO;
import com.jupiter.service.hotel.enums.Hotels;
import com.jupiter.service.hotel.dto.builder.HotelCoordinateBuilder;
import com.jupiter.service.hotel.dto.response.HotelCoordinateDTO;
import com.jupiter.service.hotel.dto.response.HotelCoordinateSearchResponseDTO;
import com.jupiter.service.hotel.dto.response.HotelSearchResponseDTO;
import com.jupiter.service.hotel.rest.client.HotelClient;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static com.jupiter.service.hotel.enums.Hotels.*;
import static com.jupiter.service.hotel.enums.RequestStatus.ERROR;
import static com.jupiter.service.hotel.enums.RequestStatus.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HotelSearchTest extends BaseTest {

    private static Stream<Arguments> hotelNameSet() {
        return Stream.of(
                Arguments.of(HOTEL_VALENCIA.getHotelName(), HOTEL_VALENCIA.getLocationName()),
                Arguments.of(HOTEL_TAC_PREMIER.getHotelName(), HOTEL_TAC_PREMIER.getLocationName()),
                Arguments.of(HOTEL_GRAND.getHotelName(), HOTEL_GRAND.getLocationName())
        );
    }

    @ParameterizedTest
    @MethodSource("hotelNameSet")
    public void searchHotelByName(String hotelName, String locationName) {
        HotelSearchResponseDTO hotelSearchResponseDTO = hotelSteps.searchHotelByName(hotelName);

        assertEquals(OK.getStatus(), hotelSearchResponseDTO.getStatus());

        hotelSearchResponseDTO.getResults().getHotels().forEach(hotel -> {
            assertTrue(hotel.getFullName().toLowerCase().contains(hotelName.toLowerCase()));
            assertTrue(hotel.getLocationName().toLowerCase().contains(locationName.toLowerCase()));
        });
    }

    private static Stream<Hotels> hotelCoordinatesSet() {
        return Stream.of(HOTEL_MILANO, HOTEL_TAC_PREMIER, HOTEL_GRAND);
    }

    @ParameterizedTest
    @MethodSource("hotelCoordinatesSet")
    public void searchHotelByCoordinate(Hotels hotels) {
        HotelCoordinateSearchResponseDTO hotelCoordinateSearchResponseDTO = hotelSteps.searchHotelByCoordinates(hotels.getLat(), hotels.getLon());

        assertEquals(OK.getStatus(), hotelCoordinateSearchResponseDTO.getStatus());

        HotelCoordinateDTO hotelCoordinateActual = hotelCoordinateSearchResponseDTO.getResults().getHotels().getFirst();
        String id = hotelCoordinateActual.getId();

        HotelCoordinateDTO hotelCoordinateExpected = HotelCoordinateBuilder.generateHotelCoordinateDTO(id, hotels);

        assertEquals(hotelCoordinateExpected, hotelCoordinateActual);
    }

    @NullAndEmptySource
    @ParameterizedTest
    public void searchHotelWithEmptyQuery(String hotelName) {
        HotelSearchResponseDTO hotelSearchResponseDTO = hotelSteps.searchHotelByName(hotelName);

        assertEquals(OK.getStatus(), hotelSearchResponseDTO.getStatus());
        assertEquals(0, hotelSearchResponseDTO.getResults().getHotels().size());
    }

    @Test
    public void searchHotelWhenRequiredParameterIsMissing() {
        HotelSearchErrorResponseDTO hotelSearchErrorResponseDTO = hotelClient.getClient().get(HotelClient.API_V2_HOTEL_SEARCH_NAME_LOCATION)
                .expectStatusCode("Incorrect status code", HttpStatus.SC_BAD_REQUEST)
                .getBodyAs(HotelSearchErrorResponseDTO.class);

        assertEquals(ERROR.getStatus(), hotelSearchErrorResponseDTO.getStatus());
        assertEquals("query: Parameter is missing", hotelSearchErrorResponseDTO.getMessage());
    }
}
