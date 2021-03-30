package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.model.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class AutoUpdateStock implements CommandLineRunner {

    private StockDto stockDto;

    private YahooApiService yahooApiService;

    @Override
    public void run(String... args) throws Exception {
        stockDto=new StockDto();
        yahooApiService=new YahooApiService();
        AutoUpdate();
    }

    public StockDto getStockDto(){
        return this.stockDto;
    }

    public void AutoUpdate(){

        final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Stock stock=yahooApiService.getSingleStock("TSLA");
                    stockDto = new StockDto().builder()
                            .symbol(stock.getSymbol())
                            .price(stock.getQuote().getPrice())
                            .change(stock.getQuote().getChange())
                            .percent(stock.getQuote().getChangeInPercent())
                            .build();

                }catch (Exception e){
                    e.printStackTrace();
                    exec.shutdown();
                }
            }
        },0,2, TimeUnit.SECONDS);
    }


}
