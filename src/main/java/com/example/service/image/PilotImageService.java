package com.example.service.image;

import com.example.entity.model.image.PilotImage;
import com.example.repository.image.PilotImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Slf4j
public class PilotImageService {

    @Autowired
    private PilotImageRepository pilotImageRepository;

    @Transactional
    public PilotImage savePilotImage(String name, MultipartFile file) {

        PilotImage pilotImageResult = null;
        try {

            pilotImageResult = PilotImage.builder()
                    .name(name.toLowerCase())
                    .image(file.getBytes())
                    .build();

            pilotImageRepository.save(pilotImageResult);

        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);
            e.printStackTrace();
        }
        return pilotImageResult;
    }

    @Transactional
    public Resource getPilotImage(String name) {
        byte[] image =
                pilotImageRepository.findByName(name.toLowerCase())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                        .getImage();

        return new ByteArrayResource(image);
    }
}
