package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.entity.Comment;
import com.jongyeon.teslagazua.entity.User;
import com.jongyeon.teslagazua.model.CommentDto;
import com.jongyeon.teslagazua.repository.CommentRepository;
import com.jongyeon.teslagazua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    private UserService userService;

    @Autowired
    public CommentService(CommentRepository commentRepository,UserService userService){
        this.commentRepository=commentRepository;
        this.userService=userService;
    }


    public List<Comment> getCommentList(){
        List<Comment> list = commentRepository.findAllByOrderByCreatedTimeDesc();
        return  list;
    }

    public Comment addComment(CommentDto resource, String userEmail){
        User user=userService.getUserByEmail(userEmail);
        if(user==null)
            return null;

        Comment comment=Comment.builder()
                .contents(resource.getContents())
                .userId(user.getId())
                .username(user.getName())
                .userEmail(user.getEmail())
                .createdTime(LocalDateTime.now())
                .build();
        return commentRepository.save(comment);
    }
}
