package com.example.service;

import com.example.entity.enums.MedalEnum;
import com.example.entity.model.Pilot;
import com.example.entity.model.exception.ResourceNotFoundException;
import com.example.repository.PilotRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class CountService implements Runnable {

    private PilotRepository pilotRepository;
    private String pilotName;
    private static int counter;
    private static final Object lock = new Object();

    public CountService(String pilotName, PilotRepository pilotRepository) {
        this.pilotName = pilotName;
        this.pilotRepository = pilotRepository;
    }

    @Override
    public void run() {
        increaseCounterForPilotAndCheckMedal();
    }

    private void increaseCounterForPilotAndCheckMedal() {
        synchronized (lock) {
            Pilot pilot = increaseCounterForPilot(pilotName);
            checkMedal(pilot);
        }
    }

    private Pilot increaseCounterForPilot(String pilotName) {
        Pilot pilot =
                pilotRepository.findByName(pilotName.toLowerCase())
                        .orElseThrow(() -> new ResourceNotFoundException("Pilot not found!"));
        counter = pilot.getCounter();
        counter++;
        pilot.setCounter(counter);
        return pilot;
    }

    private void checkMedal(Pilot pilot) {
        switch (counter) {
            case 10:
                pilot.setMedal(MedalEnum.Bronze.toString());
                System.out.printf("Sending email: To: %s Subject: You just earned your bronze medal", pilotName);
                break;
            case 100:
                pilot.setMedal(MedalEnum.Silver.toString());
                System.out.printf("Sending email: To: %s Subject: You just earned your silver medal", pilotName);
                break;
            case 1000:
                pilot.setMedal(MedalEnum.Gold.toString());
                System.out.printf("Sending email: To: %s Subject: You just earned your gold medal", pilotName);
        }
        pilotRepository.save(pilot);
    }
}
