package com.example.service.image;

import com.example.entity.model.image.CompanyImage;
import com.example.repository.image.CompanyImageRepository;
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
import java.util.Collections;

@Service
@Slf4j
public class CompanyImageService {

    @Autowired
    CompanyImageRepository companyImageRepository;

    @Transactional
    public CompanyImage saveCompanyImage(String name, MultipartFile file) {

        CompanyImage companyImageResult = null;
        try {

            companyImageResult = CompanyImage.builder()
                    .name(name.toLowerCase())
                    .image(file.getBytes())
                    .build();

            companyImageRepository.save(companyImageResult);

        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);
            e.printStackTrace();
        }
        return companyImageResult;
    }

    @Transactional
    public Resource getCompanyImage(String name) {
        byte[] image = companyImageRepository.findByName(name.toLowerCase())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getImage();

        return new ByteArrayResource(image);
    }
}
