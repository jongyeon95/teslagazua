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
        float start=a-1;
        float end=a+1;
        if(a>=0){
            if(start<0){
                start=0;
            }
        }

        else if(a<0){
           if(end>=0){
               end=0;
           }
        }
        List<Image> list = imageRepository.findByWeightBetween(start,end);
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
