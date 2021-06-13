package com.jongyeon.teslagazua.config;

import com.jongyeon.teslagazua.aop.CronLogging;
import com.jongyeon.teslagazua.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class StockScheduleConfig {


    private StockService stockService;

    @Autowired
    public StockScheduleConfig(StockService stockService) {
        this.stockService = stockService;
    }

    @CronLogging
    @Scheduled(cron = "0 0 6 * * 2-6")
    public void stopUpdate(){
        stockService.stopUpdate();
    }

    @CronLogging
    @Scheduled(cron = "57 29 22 * * 1-5")
    public void startUpdate(){
        stockService.autoUpdate();
    }

}
