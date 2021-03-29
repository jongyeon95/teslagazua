package com.jongyeon.teslagazua.controller;


import com.jongyeon.teslagazua.model.StockDto;
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

    @GetMapping("/stock/{name}")
    public StockDto getStockInfo(@PathVariable("name") String name) throws IOException {
        Stock stock=yahooApiService.getSingleStock(name);
        StockDto dto = new StockDto().builder()
                .symbol(stock.getSymbol())
                .price(stock.getQuote().getPrice())
                .change(stock.getQuote().getChange())
                .percent(stock.getQuote().getChangeInPercent())
                .build();
        System.out.println(dto.getPrice());
        return  dto;
    }
}
