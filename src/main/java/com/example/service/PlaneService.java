package com.example.service;

import com.example.entity.model.Plane;
import com.example.repository.PlaneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlaneService {

    @Autowired
    private PlaneRepository planeRepository;

    public ResponseEntity<List<Plane>> getAllPlanes() {
        List<Plane> planeList = planeRepository.findAll();
        return ResponseEntity.ok(planeList);
    }

    public ResponseEntity<List<Plane>> getAllCompanyPlanes(String company) {
        List<Plane> planeList = planeRepository.findAllByCompany(company);
        return ResponseEntity.ok(planeList);
    }

    public ResponseEntity<Plane> addPlane(Plane newPlane) {
        Plane planeResult = planeRepository.save(newPlane);
        return ResponseEntity.ok(planeResult);
    }
}
