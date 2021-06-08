package com.jongyeon.teslagazua.controller;

import com.jongyeon.teslagazua.service.ViewCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewCountController {

    @Autowired
    public ViewCountService viewCountService;

    @GetMapping("/viewCount")
    public String getToday(){
        return String.valueOf(viewCountService.getTodayViewCount().getCnt());
    }
}
