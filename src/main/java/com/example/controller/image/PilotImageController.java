package com.example.controller.image;

import com.example.entity.model.image.PilotImage;
import com.example.service.image.PilotImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pilot/image")
public class PilotImageController {

    @Autowired
    PilotImageService pilotImageService;

    @GetMapping(value = "/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getPilotImage(@PathVariable String name) {
        return ResponseEntity.ok(pilotImageService.getPilotImage(name));
    }

    @PostMapping("/add")
    public ResponseEntity<PilotImage> addPilotImage(@RequestParam("name") String name,
                                                    @RequestParam("imageFile") MultipartFile file) {
        return ResponseEntity.ok(pilotImageService.savePilotImage(name, file));
    }
}
