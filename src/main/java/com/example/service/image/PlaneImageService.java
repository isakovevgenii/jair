package com.example.service.image;

import com.example.entity.model.image.PlaneImage;
import com.example.repository.image.PlaneImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Slf4j
public class PlaneImageService {

    @Autowired
    PlaneImageRepository planeImageRepository;

    @Transactional
    public PlaneImage savePlaneImage(String planeModel, MultipartFile file) {

        PlaneImage planeImageResult = null;
        try {

            planeImageResult = PlaneImage.builder()
                    .model(planeModel.toLowerCase())
                    .image(file.getBytes())
                    .build();

            planeImageRepository.save(planeImageResult);

        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);
            e.printStackTrace();
        }
        return planeImageResult;
    }

    @Transactional
    public Resource getPlaneImage(String model) {
        byte[] image = planeImageRepository.findByModel(model.toLowerCase())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getImage();

        return new ByteArrayResource(image);
    }
}
