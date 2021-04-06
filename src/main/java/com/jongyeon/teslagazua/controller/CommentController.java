package com.jongyeon.teslagazua.controller;

import com.jongyeon.teslagazua.entity.Comment;
import com.jongyeon.teslagazua.model.CommentDto;
import com.jongyeon.teslagazua.model.SessionUser;
import com.jongyeon.teslagazua.service.CommentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    HttpSession httpSession;

    @GetMapping("/comment")
    public List<Comment> getCommentList(){
        return commentService.getComments();
    }

    @ResponseBody
    @PostMapping("/comment")
    public void addComment(@RequestBody CommentDto resource){
        SessionUser user= (SessionUser) httpSession.getAttribute("user");
        if (user!=null){
           return;
        }
        commentService.addComment(resource);
    }

}
