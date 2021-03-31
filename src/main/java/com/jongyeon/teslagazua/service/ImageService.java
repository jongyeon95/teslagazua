package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.entity.Image;
import com.jongyeon.teslagazua.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String getImageAddress(float a){
        if(a>5){
            a=5;
        }
        else if(a<-5){
            a=-5;
        }
        List<Image> list = imageRepository.findByWeightBetween(a-1,a);
        if(list.size()==0){
            if(a>=0){
                list=imageRepository.findByWeightBetween(0,100);
            }
            else{
                list=imageRepository.findByWeightBetween(0,-100);
            }
        }
        Random rand =new Random();
        int upperbound=list.size();
        int index=rand.nextInt(upperbound);
        return  list.get(index).getAddress();
    }

}
