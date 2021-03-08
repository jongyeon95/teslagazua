package com.jongyeon.teslagazua.controller;

import com.jongyeon.teslagazua.model.StockDto;
import com.jongyeon.teslagazua.service.YahooApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import yahoofinance.Stock;

import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    YahooApiService yahooApiService;

    @GetMapping("/")
    public String home(Model model) throws IOException {
        System.out.println(yahooApiService.getPeriodStock("TSLA"));
        Stock stock=yahooApiService.getPeriodStock("TSLA");
        StockDto dto = new StockDto().builder()
                .symbol(stock.getSymbol())
                .price(stock.getQuote().getPrice())
                .change(stock.getQuote().getChange())
                .percent(stock.getQuote().getChangeInPercent())
                .build();
        model.addAttribute("dto",dto);
        return "home";
    }
}
