package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.entity.ViewCount;
import com.jongyeon.teslagazua.repository.ViewCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ViewCountService {

    private ViewCountRepository viewCountRepository;

    @Autowired
    public ViewCountService(ViewCountRepository viewCountRepository){
        this.viewCountRepository=viewCountRepository;
    }

    public void createViewCount(){
        viewCountRepository.save(ViewCount.builder().date(LocalDate.now()).cnt(0L).build());
    }

    public void increaseViewCount(){
        LocalDate localDate=LocalDate.now();
        ViewCount viewCount = viewCountRepository.findByDate(localDate);
        viewCount.setCnt(viewCount.getCnt()+1);
        viewCountRepository.save(viewCount);
    }

    public ViewCount getTodayViewCount(){
        return viewCountRepository.findByDate(LocalDate.now());
    }

}
