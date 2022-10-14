package com.example.controller.image;

import com.example.entity.model.image.PlaneImage;
import com.example.service.image.PlaneImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/plane/image")
public class PlaneImageController {

    @Autowired
    private PlaneImageService planeImageService;

    @GetMapping(value = "/{model}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getPlaneImage(@PathVariable String model) {
        return ResponseEntity.ok(planeImageService.getPlaneImage(model));
    }

    @PostMapping("/add")
    public ResponseEntity<PlaneImage> addPlaneImage(@RequestParam("modelName") String modelName,
                                                    @RequestParam("imageFile") MultipartFile file) {
        return ResponseEntity.ok(planeImageService.savePlaneImage(modelName, file));
    }
}
