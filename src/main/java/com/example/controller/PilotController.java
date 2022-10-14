package com.example.controller;

import com.example.entity.model.Pilot;
import com.example.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pilot")
public class PilotController {

    @Autowired
    private PilotService pilotService;

    @GetMapping("/all")
    public ResponseEntity<List<Pilot>> getAllPilot() {
        return pilotService.getAllPilot();
    }

    // TODO Define booked flight related controllers - eg. add new flight
    @PostMapping("/add")
    public ResponseEntity<Pilot> createPilot(@RequestBody Pilot newPilot) {
        return pilotService.savePilot(newPilot);
    }
}
