package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.entity.Comment;
import com.jongyeon.teslagazua.entity.User;
import com.jongyeon.teslagazua.model.CommentDto;
import com.jongyeon.teslagazua.repository.CommentRepository;
import com.jongyeon.teslagazua.repository.UserRepository;


import com.jongyeon.teslagazua.role.Role;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class CommentServiceTest {

    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        commentService=new CommentService(commentRepository,userRepository);
    }

    @Test
    public void getList(){
        List<Comment> MockList =  new ArrayList<>();
        MockList.add(Comment.builder()
                .id(1L)
                .userEmail("test@test.com")
                .userId(1L)
                .username("test")
                .build());

        given(commentRepository.findAllByOrderByCreatedTimeDesc()).willReturn(MockList);
        List<Comment> list = commentService.getCommentList();
        assertThat(list.get(0).getUsername(),is("test"));
    }

    @Test
    public void addComment(){
        CommentDto dto=CommentDto.builder()
                .contents("test")
                .build();
        User mockUser = User.builder()
                .name("tester")
                .email("test@test.com")
                .role(Role.ADMIN)
                .build();


        given(userRepository.findByEmail("test@test.com")).willReturn(Optional.ofNullable(mockUser));
        commentService.addComment(dto,"test@test.com");
        verify(commentRepository).save(any());
    }

}