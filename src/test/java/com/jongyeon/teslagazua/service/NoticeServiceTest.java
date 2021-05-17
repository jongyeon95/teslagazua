package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.entity.Notice;
import com.jongyeon.teslagazua.entity.User;
import com.jongyeon.teslagazua.model.NoticeDto;
import com.jongyeon.teslagazua.repository.NoticeRepository;
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
import static org.mockito.BDDMockito.given;

public class NoticeServiceTest {

    private NoticeService noticeService;

    @Mock
    private NoticeRepository noticeRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        noticeService=new NoticeService(noticeRepository,userRepository);
    }

    @Test
    public void getNoticeList(){
        List<Notice> mockList= new ArrayList<>();

        Notice mockNotice= Notice.builder()
                .title("test title")
                .content("test content")
                .userid(1L)
                .build();

        User mockUser = User.builder()
                .id(1L)
                .name("tester")
                .email("test@test.com")
                .role(Role.ADMIN)
                .build();
        
        mockList.add(mockNotice);

        given(noticeRepository.findAllByOrderByIdDesc()).willReturn(mockList);
        given(userRepository.findById(1L)).willReturn(Optional.ofNullable(mockUser));
        List<NoticeDto> list = noticeService.getNoticeList();
        assertThat(list.get(0).getUser().getName(),is("tester"));
        assertThat(list.get(0).getUser().getId(),is(1L));
    }
}