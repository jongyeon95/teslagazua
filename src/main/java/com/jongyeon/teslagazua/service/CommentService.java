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

    private UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository){
        this.commentRepository=commentRepository;
        this.userRepository=userRepository;
    }


    public List<Comment> getCommentList(){
        List<Comment> list = commentRepository.findAllByOrderByCreatedTimeDesc();
        for(int i=0; i<list.size(); i++){
            String origin=list.get(i).getUsername();
            StringBuilder sb=new StringBuilder();
            for(int j=0; j<origin.length(); j++){
                if(j%2==1)
                    sb.append("*");
                else
                    sb.append(origin.charAt(j));
            }
            list.get(i).setUsername(sb.toString());

        }
        return  list;
    }

    public Comment addComment(CommentDto resource, String userEmail){
        User user=userRepository.findByEmail(userEmail).orElse(null);
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
