package com.example.controller;

import com.example.entity.model.Plane;
import com.example.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plane")
public class PlaneController {

    @Autowired
    PlaneService planeService;

    @GetMapping("/all")
    public ResponseEntity<List<Plane>> getAllPlanes() {
        return planeService.getAllPlanes();
    }

    @GetMapping("/{company}")
    public ResponseEntity<List<Plane>> getAllCompanyPlanes(@PathVariable String company) {
        return planeService.getAllCompanyPlanes(company);
    }

    @PostMapping("/add")
    public ResponseEntity<Plane> createFlight(@RequestBody Plane newPlane) {
        return planeService.addPlane(newPlane);
    }
}