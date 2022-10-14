package com.example.controller.image;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaviconController {

    @GetMapping("favicon.ico")
    public void returnNoFavicon() {
        // No favicon
    }
}
