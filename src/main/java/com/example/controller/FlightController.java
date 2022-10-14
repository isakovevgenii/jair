package com.example.controller;

import com.example.entity.model.Flight;
import com.example.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlight() {
        return flightService.getAllFlight();
    }

//    @GetMapping("/image/all")
//    public ResponseEntity<List<FlightImage>> getAllImageFlight() {
//        return flightService.getAllImageFlight();
//    }

    // TODO Define booked flight related controllers - eg. add new flight
    @PostMapping("/add")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight newFlight) {
        return flightService.saveFlight(newFlight);
    }
}
