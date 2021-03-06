package com.jongyeon.teslagazua.config;

import com.jongyeon.teslagazua.aop.CronLogging;
import com.jongyeon.teslagazua.service.ImageService;
import com.jongyeon.teslagazua.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class ImageScheduleConfig {


    private ImageService imageService;

    @Autowired
    public ImageScheduleConfig(ImageService imageService) {
        this.imageService = imageService;
    }

    @CronLogging
    @Scheduled(cron = "0 0 6 * * 2-6")
    public void stopUpdate(){
       imageService.stopUpdate();
    }

    @CronLogging
    @Scheduled(cron = "59 29 22 * * 1-5")
    public void startUpdate(){
       imageService.autoUpdate();
    }
}
