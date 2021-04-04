package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.entity.Comment;
import com.jongyeon.teslagazua.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments(){
        List<Comment> list = commentRepository.findAll();
        return  list;
    }
}
