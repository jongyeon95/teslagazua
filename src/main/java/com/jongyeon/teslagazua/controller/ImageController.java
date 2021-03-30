package com.jongyeon.teslagazua.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    @GetMapping("/image/{weight}")
    public String getImage(@PathVariable("weight") float weight){
        return "https://t1.daumcdn.net/news/202103/17/fmkorea/20210317153458396otin.gif";
    }
}
