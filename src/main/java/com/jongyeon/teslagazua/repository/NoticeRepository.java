package com.jongyeon.teslagazua.repository;

import com.jongyeon.teslagazua.entity.Notice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice,Long> {
    List<Notice> findAllByOrderByIdDesc();

}
