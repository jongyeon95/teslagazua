package com.jongyeon.teslagazua.controller;


import com.jongyeon.teslagazua.model.StockDto;
import com.jongyeon.teslagazua.service.StockService;
import com.jongyeon.teslagazua.service.YahooApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class StockController {

    @Autowired
    YahooApiService yahooApiService;

    @Autowired
    StockService stockService;

    @GetMapping("/stock")
    public StockDto getStockInfo() throws IOException {
        return  stockService.getStockDto();
    }


}
