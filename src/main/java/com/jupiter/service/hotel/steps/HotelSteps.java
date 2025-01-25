package com.jupiter.service.hotel.steps;

import com.jupiter.common.annotation.Steps;
import com.jupiter.service.hotel.dto.response.HotelCoordinateSearchResponseDTO;
import com.jupiter.service.hotel.dto.response.HotelPriceResponseDTO;
import com.jupiter.service.hotel.dto.response.HotelSearchResponseDTO;
import com.jupiter.service.hotel.rest.controller.HotelController;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;

@Steps
@Log4j2
public class HotelSteps {

    @Autowired
    private HotelController hotelController;

    @Step("Search hotel by name")
    public HotelSearchResponseDTO searchHotelByName(String hotelName) {
        log.info("Search hotel by name={}", hotelName);

        return hotelController
                .getHotelsData(hotelName, SC_OK)
                .getBodyAs(HotelSearchResponseDTO.class);
    }

    @Step("Search hotel by coordinates")
    public HotelCoordinateSearchResponseDTO searchHotelByCoordinates(Double latitude, Double longitude) {
        log.info("Search hotel by coordinates latitude={}, longitude={}", latitude, longitude);

        return hotelController
                .getHotelsData(latitude, longitude, SC_OK)
                .getBodyAs(HotelCoordinateSearchResponseDTO.class);
    }

    @Step("Get hotel prices by location name")
    public List<HotelPriceResponseDTO> getHotelPriceByLocation(String locationName, LocalDate checkInDate, LocalDate checkOutDate) {
        log.info("Get hotel price by location={} from {} to {} dates", locationName, checkInDate, checkOutDate);

        return hotelController
                .getHotelLivingCost(locationName, checkInDate, checkOutDate, SC_OK)
                .getBodyAsList(HotelPriceResponseDTO.class);
    }

    @Step("Get hotel prices by location id")
    public List<HotelPriceResponseDTO> getHotelPriceByLocationId(Integer locationId, LocalDate checkInDate, LocalDate checkOutDate) {
        log.info("Get hotel price by locationId={} from {} to {} dates", locationId, checkInDate, checkOutDate);

        return hotelController
                .getHotelLivingCost(locationId, checkInDate, checkOutDate, SC_OK)
                .getBodyAsList(HotelPriceResponseDTO.class);
    }
}
