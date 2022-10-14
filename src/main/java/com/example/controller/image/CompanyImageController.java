package com.example.controller.image;

import com.example.entity.model.image.CompanyImage;
import com.example.service.image.CompanyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/company/image")
public class CompanyImageController {

    @Autowired
    CompanyImageService companyImageService;

    @GetMapping(value = "/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getCompanyImage(@PathVariable String name) {
        return ResponseEntity.ok(companyImageService.getCompanyImage(name));
    }

    @PostMapping("/add")
    public ResponseEntity<CompanyImage> addCompanyImage(@RequestParam("name") String name,
                                                        @RequestParam("imageFile") MultipartFile file) {
        return ResponseEntity.ok(companyImageService.saveCompanyImage(name, file));
    }
}
