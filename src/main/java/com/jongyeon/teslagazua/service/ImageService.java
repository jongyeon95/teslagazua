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


    //이미지 정보 Dto
    private ImageDto imageDto;

    //스케줄 멤버
    private ScheduledThreadPoolExecutor exec;


    private ImageRepository imageRepository;

    private StockService stockService;

    @Autowired
    public ImageService(StockService stockService, ImageRepository imageRepository){
        this.imageRepository=imageRepository;
        this.stockService=stockService;
    }



    // 서버 구동시 시작
    @Override
    public void run(String... args) throws Exception {
        this.imageDto = new ImageDto();
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

        List<Image> list;

        if(change>=0){
           list=imageRepository.findAllByWeightIsGreaterThanEqual(0);
        }
        else{
            list=imageRepository.findAllByWeightLessThanEqual(0);
        }

        List<String> addressList = new ArrayList<>();

        for(Image temp : list){
            addressList.add(temp.getAddress());
        }
        this.imageDto.setImageAddress(addressList);
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

    //이미지 가져오기
    public String getImageAddress(){
        Random rand =new Random();
        int upperbound=imageDto.getImageAddress().size();
        int index=0;
        if(upperbound!=0)
            index=rand.nextInt(upperbound);
        else
            return "Not Found";

        return  imageDto.getImageAddress().get(index);
    }

    //이미지 추가
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
