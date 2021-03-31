package com.jongyeon.teslagazua.repository;

import com.jongyeon.teslagazua.entity.Image;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ImageRepository extends CrudRepository<Image,Long> {
    List<Image> findByWeightBetween(float a, float b);
}
