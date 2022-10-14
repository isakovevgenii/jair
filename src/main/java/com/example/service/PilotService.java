package com.example.service;

import com.example.entity.model.Pilot;
import com.example.repository.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotService {

    @Autowired
    private PilotRepository pilotRepository;

    public ResponseEntity<List<Pilot>> getAllPilot() {
        List<Pilot> pilotList = pilotRepository.findAll();
        return ResponseEntity.ok(pilotList);
    }

    public ResponseEntity<Pilot> savePilot(Pilot newPilot) {
        Pilot pilotResult = pilotRepository.save(newPilot);
        return ResponseEntity.ok(pilotResult);
    }
}
