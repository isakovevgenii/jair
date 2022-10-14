package com.example.service;

import com.example.entity.model.Flight;
import com.example.entity.model.exception.PilotFeelsSickException;
import com.example.entity.model.exception.SamePilotAndCopilotException;
import com.example.repository.FlightRepository;
import com.example.repository.PilotRepository;
import com.example.service.image.CompanyImageService;
import com.example.service.image.PilotImageService;
import com.example.service.image.PlaneImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PilotRepository pilotRepository;

    @Autowired
    CompanyImageService companyImageService;

    @Autowired
    PilotImageService pilotImageService;

    @Autowired
    PlaneImageService planeImageService;

    public ResponseEntity<List<Flight>> getAllFlight() {
        List<Flight> flightList = flightRepository.findAll();
        return ResponseEntity.ok(flightList);
    }

    public ResponseEntity<Flight> saveFlight(Flight flight) {
        if (checkingSamePilotAndCopilot(flight)) throw new SamePilotAndCopilotException();
        if (checkPilotHealth(flight.getPilot()) || checkPilotHealth(flight.getCopilot()))
            throw new PilotFeelsSickException();
        Flight flightResult = flightRepository.save(flight);
        countBook(flight);
        return ResponseEntity.ok(flightResult);
    }

    public boolean checkingSamePilotAndCopilot(Flight flight) {
        return flight.getPilot().equals(flight.getCopilot());
    }

    public boolean checkPilotHealth(String pilot) {
        return new Random().nextInt(100) <= 10;
    }

    public void countBook(Flight flight) {
        new CountService(flight.getPilot(), pilotRepository).run();
        new CountService(flight.getCopilot(), pilotRepository).run();
    }

//    public ResponseEntity<List<FlightImage>> getAllImageFlight() {
//        List<FlightImage> flightImageList = new ArrayList<>();
//        for (Flight flight : flightRepository.findAll()) {
//            flightImageList.add(flightToImageFlight(flight));
//        }
//        return ResponseEntity.ok(flightImageList);
//    }

//    private FlightImage flightToImageFlight(Flight flight) {
//
//        return FlightImage.builder()
//                .airline(companyImageService.getCompanyImage(flight.getAirline()))
//                .airplane(planeImageService.getPlaneImage(flight.getAirplane()))
//                .pilot(pilotImageService.getPilotImage(flight.getPilotFirstName(), flight.getPilotSecondName()))
//                .copilot(pilotImageService.getPilotImage(flight.getCopilotFirstName(), flight.getCopilotSecondName()))
//                .build();
//    }

//    private List<Resource> flightToListResourse(Flight flight) {
//        return List.of(
//                companyImageService.getCompanyImage(flight.getAirline()),
//                planeImageService.getPlaneImage(flight.getAirplane()),
//                pilotImageService.getPilotImage(flight.getPilotFirstName(), flight.getPilotSecondName()),
//                pilotImageService.getPilotImage(flight.getCopilotFirstName(), flight.getCopilotSecondName()));
//    }
}
