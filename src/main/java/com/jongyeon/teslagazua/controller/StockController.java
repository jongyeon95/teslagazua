package com.jongyeon.teslagazua.controller;


import com.jongyeon.teslagazua.model.StockDto;
import com.jongyeon.teslagazua.service.AutoUpdateStock;
import com.jongyeon.teslagazua.service.YahooApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;

import java.io.IOException;

@RestController
public class StockController {

    @Autowired
    YahooApiService yahooApiService;

    @Autowired
    AutoUpdateStock autoUpdateStock;

    @GetMapping("/stock")
    public StockDto getStockInfo() throws IOException {
        return  autoUpdateStock.getStockDto();
    }


}
