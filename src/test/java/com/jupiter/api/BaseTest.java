package com.jupiter.api;

import com.jupiter.service.hotel.rest.client.HotelClient;
import com.jupiter.service.hotel.rest.controller.HotelController;
import com.jupiter.service.hotel.steps.HotelSteps;
import com.jupiter.service.reqres.rest.controller.ReqresController;
import com.jupiter.service.reqres.steps.ReqresSteps;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@SpringBootConfiguration
@Execution(ExecutionMode.CONCURRENT)
@ComponentScan(basePackages = {"com.jupiter.*"})
public class BaseTest {

    @Autowired
    protected ReqresSteps reqresSteps;
    @Autowired
    protected HotelSteps hotelSteps;

    @Autowired
    protected ReqresController reqresController;
    @Autowired
    protected HotelController hotelController;

    @Autowired
    protected HotelClient hotelClient;
}
