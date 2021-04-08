package com.jongyeon.teslagazua.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String address;

    private float weight;
}
