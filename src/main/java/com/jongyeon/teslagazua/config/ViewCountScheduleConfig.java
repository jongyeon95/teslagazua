package com.jongyeon.teslagazua.config;

import com.jongyeon.teslagazua.aop.CronLogging;
import com.jongyeon.teslagazua.service.StockService;
import com.jongyeon.teslagazua.service.ViewCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class ViewCountScheduleConfig {


    private ViewCountService viewCountService;

    @Autowired
    public ViewCountScheduleConfig(ViewCountService viewCountService) {
        this.viewCountService = viewCountService;
    }

    @CronLogging
    @Scheduled(cron = " 0 0 0 * * *")
    public void createViewCount(){
        viewCountService.createViewCount();
    }

}
