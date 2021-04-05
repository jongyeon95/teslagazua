package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.entity.Comment;
import com.jongyeon.teslagazua.model.CommentDto;
import com.jongyeon.teslagazua.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments(){
        List<Comment> list = commentRepository.findAll();
        return  list;
    }

    public Comment addComment(CommentDto resource){
        Comment comment=Comment.builder()
                .contents(resource.getContents())
                .userId(resource.getUserId())
                .username(resource.getUsername())
                .createdTime(LocalDateTime.now())
                .build();
        return commentRepository.save(comment);
    }
}
