package com.jongyeon.teslagazua.controller;

import com.jongyeon.teslagazua.entity.Comment;
import com.jongyeon.teslagazua.model.CommentDto;
import com.jongyeon.teslagazua.service.CommentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;


    @GetMapping("/comment")
    public List<Comment> getCommentList(){
        return commentService.getComments();
    }

    @PostMapping("/comment")
    public void addComment(@RequestBody CommentDto resource){
        commentService.addComment(resource);
    }

}
