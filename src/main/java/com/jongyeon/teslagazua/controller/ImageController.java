package com.jongyeon.teslagazua.controller;

import com.jongyeon.teslagazua.entity.Image;
import com.jongyeon.teslagazua.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping("/images")
    public String getImage(){
        return imageService.getImageAddress();
    }

    @PostMapping("/images")
    public HttpStatus addImage(@RequestBody Image resource){
        return imageService.addImage(resource.getAddress(),resource.getWeight());
    }

    @DeleteMapping("/images/{id}")
    public HttpStatus deleteImage(@PathVariable("id") Long id){
        return imageService.deleteImage(id);
    }

}
