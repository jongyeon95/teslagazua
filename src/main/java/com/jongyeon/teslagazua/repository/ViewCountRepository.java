package com.jongyeon.teslagazua.repository;

import com.jongyeon.teslagazua.entity.ViewCount;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface ViewCountRepository extends CrudRepository<ViewCount, Long> {
    ViewCount findByDate(LocalDate localDate);
}
