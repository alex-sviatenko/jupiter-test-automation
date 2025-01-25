package com.jupiter.service.hotel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Locations {
    LOCATION_ALANYA("Alanya, Turkey", 2696, "Alanya", "Turkey"),
    LOCATION_VALENCIA("Valencia, Spain", 4164, "Valencia", "Spain"),
    LOCATION_BOURGAS("Bourgas, Bulgaria", 609, "Bourgas", "Bulgaria"),
    LOCATION_PORTOROZ("Portoroz, Slovenia", 3035, "Portoroz", "Slovenia");

    private final String locationName;
    private final Integer locationId;
    private final String name;
    private final String country;
}
