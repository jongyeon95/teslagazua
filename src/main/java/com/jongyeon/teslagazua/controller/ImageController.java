package com.jongyeon.teslagazua.controller;

import com.jongyeon.teslagazua.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping("/images")
    public String getImage(){
        return imageService.getImageAddress();
    }

}
