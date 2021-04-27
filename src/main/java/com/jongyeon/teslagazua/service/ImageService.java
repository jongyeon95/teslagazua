package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.entity.Image;
import com.jongyeon.teslagazua.model.ImageDto;
import com.jongyeon.teslagazua.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class ImageService implements CommandLineRunner {


    private ImageDto imageDto;
    private ScheduledThreadPoolExecutor exec;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private StockService stockService;

    @Override
    public void run(String... args) throws Exception {
        imageDto = new ImageDto();
        imageDto.getImageAddress().add(imageRepository.findByWeightBetween(0,1).get(0).getAddress());

    }


    //가격에 맞는 이미지 리스트 최신화
    public void setImageList(){
        BigDecimal changeB;
        if(stockService!=null)
            changeB=stockService.getStockDto().getChange();
        else
            changeB=BigDecimal.valueOf(0);

        if(changeB==null)
            changeB=BigDecimal.valueOf(0);

        float change= changeB.floatValue();
        float start=change-1;
        float end=change+1;

        if(change>=0){
            if(start<0){
                start=0;
            }
        }
        else if(change<0){
            if(end>=0){
                end=0;
            }
        }
        List<Image> list = imageRepository.findByWeightBetween(start,end);

        if(list.size()==0){
            if(change>=0){
                list=imageRepository.findByWeightBetween(0,100);
            }
            else{
                list=imageRepository.findByWeightBetween(0,-100);
            }
        }

        List<String> addressList = new ArrayList<>();
        for(Image temp : list){
            addressList.add(temp.getAddress());
        }
        imageDto.setImageAddress(addressList);
    }

    //업데이트 중지
    public void stopUpdate(){
        exec.shutdown();
    }

    //업데이트 시작
    public void autoUpdate(){
        exec = new ScheduledThreadPoolExecutor(1);
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    setImageList();
                }catch (Exception e){
                    e.printStackTrace();
                    exec.shutdown();
                }
            }
            },0,10, TimeUnit.SECONDS);
    }

    public String getImageAddress(){
        Random rand =new Random();
        int upperbound=imageDto.getImageAddress().size();
        int index=0;
        if(upperbound!=0)
            index=rand.nextInt(upperbound);
        return  imageDto.getImageAddress().get(index);
    }

    public HttpStatus addImage(String address , float weight){
        if(address==null)
            return HttpStatus.BAD_REQUEST;
        Image newImage=Image.builder()
                .address(address)
                .weight(weight)
                .build();
        imageRepository.save(newImage);
        return HttpStatus.OK;
    }


}
