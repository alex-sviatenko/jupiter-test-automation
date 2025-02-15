package com.jupiter.service.hotel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static com.jupiter.service.hotel.enums.Locations.*;

@Getter
@ToString
@AllArgsConstructor
public enum Hotels {

    HOTEL_MILANO("Milano Hotel", LOCATION_BOURGAS.getLocationName(), LOCATION_BOURGAS.getLocationId(), 42.51981, 27.46851),
    HOTEL_GRAND("Grand Hotel Bernardin", LOCATION_PORTOROZ.getLocationName(), LOCATION_PORTOROZ.getLocationId(), 45.51702, 13.5697),

    HOTEL_VALENCIA("Hotel Valencia Center", LOCATION_VALENCIA.getLocationName(), LOCATION_VALENCIA.getLocationId(), 39.45994, -0.34948),
    HOTEL_LAS_ARENAS("Las Arenas Balneario Resort", LOCATION_VALENCIA.getLocationName(), LOCATION_VALENCIA.getLocationId(), 39.469752, -0.377387),
    HOTEL_EUROSTARS("Eurostars Rey Don Jaime", LOCATION_VALENCIA.getLocationName(), LOCATION_VALENCIA.getLocationId(), 39.469752, -0.377387),
    HOTEL_MALCOM("Hotel Malcom and Barret", LOCATION_VALENCIA.getLocationName(), LOCATION_VALENCIA.getLocationId(), 39.469752, -0.377387),
    HOTEL_VENECIA("Venecia Plaza Centro", LOCATION_VALENCIA.getLocationName(), LOCATION_VALENCIA.getLocationId(), 39.469752, -0.377387),

    HOTEL_GREEN_GARDEN("Green Garden Suites Hotel", LOCATION_ALANYA.getLocationName(), LOCATION_ALANYA.getLocationId(), 36.545233, 31.992696),
    HOTEL_HARMONY("Harmony Butik Otel", LOCATION_ALANYA.getLocationName(), LOCATION_ALANYA.getLocationId(), 36.545233, 31.992696),
    HOTEL_SUNWAY("Sunway Apart Hotel", LOCATION_ALANYA.getLocationName(), LOCATION_ALANYA.getLocationId(), 36.545233, 31.992696),
    HOTEL_BLUE_HEAVEN("Blue Heaven Apart Hotel", LOCATION_ALANYA.getLocationName(), LOCATION_ALANYA.getLocationId(), 36.545235, 31.992697),
    HOTEL_MUSTI("Musti Apart Hotel", LOCATION_ALANYA.getLocationName(), LOCATION_ALANYA.getLocationId(), 36.545233, 31.992696),
    HOTEL_TAC_PREMIER("Tac Premier Hotel & Spa", LOCATION_ALANYA.getLocationName(), LOCATION_ALANYA.getLocationId(), 36.54899, 31.98159);

    private final String hotelName;
    private final String locationName;
    private final Integer locationId;
    private final Double lat;
    private final Double lon;
}
