package com.jongyeon.teslagazua.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ImageDto {

    private List<String> imageAddress;

    public ImageDto(){
        imageAddress=new ArrayList<>();
    }

}
