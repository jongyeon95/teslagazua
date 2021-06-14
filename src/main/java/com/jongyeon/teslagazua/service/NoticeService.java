package com.jongyeon.teslagazua.service;

import com.jongyeon.teslagazua.entity.Notice;
import com.jongyeon.teslagazua.exception.IdNotFoundException;
import com.jongyeon.teslagazua.model.NoticeDto;
import com.jongyeon.teslagazua.repository.NoticeRepository;
import com.jongyeon.teslagazua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeService {


    private NoticeRepository noticeRepository;

    private UserService userService;

    @Autowired
    NoticeService(NoticeRepository noticeRepository, UserService userService){
        this.noticeRepository=noticeRepository;
        this.userService=userService;
    }

    //게시글 목록 불러오기
    public List<NoticeDto> getNoticeList(){
        List<Notice> list = noticeRepository.findAllByOrderByIdDesc();
        List<NoticeDto> dList=new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            NoticeDto dto = NoticeDto.builder()
                    .user(userService.getUserById(list.get(i).getUserid()))
                    .id(list.get(i).getId())
                    .title(list.get(i).getTitle())
                    .createdAt(list.get(i).getCreatedAt())
                    .view(list.get(i).getView())
                    .build();
            dList.add(dto);
        }
        return dList;

    }

    //게시글 상세 가져오기
    public NoticeDto getNotice(Long id){
        Notice notice = noticeRepository.findById(id).orElseThrow(()-> new IdNotFoundException());
        NoticeDto dto = NoticeDto.builder()
                .user(userService.getUserById(notice.getUserid()))
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .view(notice.getView()+1)
                .build();
        updateNotice(dto);
        return dto;
    }


    //게시글 추가
    public Notice addNotice(NoticeDto dto){
        Notice notice = Notice.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .userid(dto.getUserid())
                .view(0L)
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();
        return noticeRepository.save(notice);
    }

    //게시글 수정
    public Notice updateNotice(NoticeDto dto){
        Notice notice = Notice.builder()
                .id(dto.getId())
                .userid(dto.getUser().getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .view(dto.getView())
                .createdAt(dto.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
        return noticeRepository.save(notice);
    }

}
