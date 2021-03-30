package com.jongyeon.teslagazua.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class GifImage {
    @Id
    @GeneratedValue
    private Long id;

    private String address;

    private int weight;
}
