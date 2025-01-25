package com.jupiter.api.hotel;

import com.jupiter.api.BaseTest;
import com.jupiter.service.hotel.dto.response.HotelPriceResponseDTO;
import com.jupiter.service.hotel.dto.response.HotelSearchErrorResponseDTO;
import com.jupiter.service.hotel.enums.Hotels;
import com.jupiter.service.hotel.rest.client.HotelClient;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.jupiter.service.hotel.enums.Hotels.*;
import static com.jupiter.service.hotel.enums.Locations.LOCATION_ALANYA;
import static com.jupiter.service.hotel.enums.Locations.LOCATION_VALENCIA;
import static com.jupiter.service.hotel.enums.RequestStatus.ERROR;
import static com.jupiter.utils.DateTimeUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class HotelLivingCostTest extends BaseTest {

    private static Stream<Arguments> hotelDataSet() {
        return Stream.of(
                Arguments.of(LOCATION_VALENCIA.getName(), List.of(HOTEL_LAS_ARENAS, HOTEL_EUROSTARS, HOTEL_MALCOM, HOTEL_VENECIA), 1),
                Arguments.of(LOCATION_ALANYA.getName(), List.of(HOTEL_GREEN_GARDEN, HOTEL_HARMONY, HOTEL_SUNWAY, HOTEL_MUSTI), 14)
        );
    }

    @ParameterizedTest
    @MethodSource("hotelDataSet")
    public void displayHotelPriceWhenLocationNameProvided(String locationName, List<Hotels> hotelListExpected, Integer days) {
        LocalDate checkInDate = getDateNow();
        LocalDate checkOutDate = getFutureDate(days);
        List<HotelPriceResponseDTO> hotelListActual = hotelSteps.getHotelPriceByLocation(locationName, checkInDate, checkOutDate);

        checkHotelDataAndPrice(hotelListExpected, hotelListActual);
    }

    private static Stream<Arguments> hotelLocationIdDataSet() {
        return Stream.of(
                Arguments.of(LOCATION_VALENCIA.getLocationId(), List.of(HOTEL_LAS_ARENAS, HOTEL_EUROSTARS, HOTEL_MALCOM, HOTEL_VENECIA), 1),
                Arguments.of(LOCATION_ALANYA.getLocationId(), List.of(HOTEL_GREEN_GARDEN, HOTEL_HARMONY, HOTEL_SUNWAY, HOTEL_MUSTI), 7)
        );
    }

    @ParameterizedTest
    @MethodSource("hotelLocationIdDataSet")
    public void displayHotelPriceWhenLocationIdProvided(Integer locationId, List<Hotels> hotelListExpected, Integer days) {
        LocalDate checkInDate = getDateNow();
        LocalDate checkOutDate = getFutureDate(days);
        List<HotelPriceResponseDTO> hotelListActual = hotelSteps.getHotelPriceByLocationId(locationId, checkInDate, checkOutDate);

        checkHotelDataAndPrice(hotelListExpected, hotelListActual);
    }

    @Test
    public void checkHotelDataForPastPeriod() {
        LocalDate checkInDate = getPastDate(10);
        LocalDate checkOutDate = getPastDate(1);
        List<HotelPriceResponseDTO> hotelsListActual = hotelSteps.getHotelPriceByLocation(LOCATION_ALANYA.getLocationName(), checkInDate, checkOutDate);

        assertEquals(0, hotelsListActual.size());
    }

    @Test
    public void displayHotelPriceWhenRequiredParameterIsMissing() {
        HotelSearchErrorResponseDTO hotelSearchErrorResponseDTO = hotelClient.getClient().get(HotelClient.API_V2_HOTEL_LIVING_COST)
                .expectStatusCode("Incorrect status code", HttpStatus.SC_BAD_REQUEST)
                .getBodyAs(HotelSearchErrorResponseDTO.class);

        assertEquals(ERROR.getStatus(), hotelSearchErrorResponseDTO.getStatus());
        assertEquals("locationId: Parameter is missing", hotelSearchErrorResponseDTO.getMessage());
    }

    private void checkHotelDataAndPrice(List<Hotels> hotelListExpected, List<HotelPriceResponseDTO> hotelListActual) {
        assertEquals(hotelListExpected.size(), hotelListActual.size(), "hotels list size is incorrect");

        hotelListExpected.forEach(hotelExpected -> {
            Optional<HotelPriceResponseDTO> hotelOpt = hotelListActual.stream()
                    .filter(hotel -> hotel.getHotelName().equals(hotelExpected.getHotelName()))
                    .findFirst();

            assertNotNull(hotelOpt.get());

            HotelPriceResponseDTO hotelActual = hotelOpt.get();

            assertEquals(hotelExpected.getLocationId(), hotelActual.getLocationId());
            assertEquals(hotelExpected.getHotelName(), hotelActual.getHotelName());

            assertTrue(hotelActual.getPriceFrom() != null && hotelActual.getPriceFrom() > 0);
            assertTrue(hotelActual.getPriceAvg() != null && hotelActual.getPriceAvg() > 0);
        });
    }
}
