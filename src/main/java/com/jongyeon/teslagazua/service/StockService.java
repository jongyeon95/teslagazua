package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.model.StockDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class StockService implements CommandLineRunner {

    private StockDto stockDto;
    private ScheduledThreadPoolExecutor exec;
    private YahooApiService yahooApiService;

    @Override
    public void run(String... args) throws Exception {

        yahooApiService=new YahooApiService();
        stockDto = setStockDto();
        exec = new ScheduledThreadPoolExecutor(1);

    }

    public StockDto getStockDto(){
        return this.stockDto;
    }


    public void stopUpdate(){
        exec.shutdown();
    }


    public void autoUpdate(){

        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    stockDto = setStockDto();
                }catch (Exception e){
                    e.printStackTrace();
                    exec.shutdown();
                }
            }
        },0,2, TimeUnit.SECONDS);
    }

    public StockDto setStockDto() throws IOException {
        Stock stock=yahooApiService.getSingleStock("TSLA");
        return new StockDto().builder()
                .symbol(stock.getSymbol())
                .price(stock.getQuote().getPrice())
                .change(stock.getQuote().getChange())
                .percent(stock.getQuote().getChangeInPercent())
                .build();
    }


}
