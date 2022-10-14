package com.example.service;

import com.example.entity.model.Flight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlightServiceTest {

    @Autowired
    FlightService flightService;

    @Autowired
    CompanyService companyService;

    @Test
    public void checkingSamePilotAndCopilotFalseTest() {
        Flight flight = new Flight(
                1,
                "lufthansa",
                "airbasa350",
                "james doolittle",
                "robert hoover"
        );
        Assertions.assertFalse(flightService.checkingSamePilotAndCopilot(flight));
    }

    @Test
    public void checkingSamePilotAndCopilotTrueTest() {
        Flight flight = new Flight(
                1,
                "lufthansa",
                "airbasa350",
                "james doolittle",
                "james doolittle"
        );
        Assertions.assertTrue(flightService.checkingSamePilotAndCopilot(flight));
    }
}
