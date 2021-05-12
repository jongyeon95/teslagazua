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

    private UserRepository userRepository;

    @Autowired
    NoticeService(NoticeRepository noticeRepository, UserRepository userRepository){
        this.noticeRepository=noticeRepository;
        this.userRepository=userRepository;
    }


    public List<NoticeDto> getNoticeList(){
        List<Notice> list = noticeRepository.findAllByOrderByIdDesc();
        List<NoticeDto> dList=new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            NoticeDto dto = NoticeDto.builder()
                    .user(userRepository.findById(list.get(i).getUserid()).orElseThrow(()-> new IdNotFoundException()))
                    .id(list.get(i).getId())
                    .title(list.get(i).getTitle())
                    .createdAt(list.get(i).getCreatedAt())
                    .view(list.get(i).getView())
                    .build();
            dList.add(dto);
        }
        return dList;

    }

    public NoticeDto getNotice(Long id){
        Notice notice = noticeRepository.findById(id).orElseThrow(()-> new IdNotFoundException());
        NoticeDto dto = NoticeDto.builder()
                .user(userRepository.findById(notice.getUserid()).orElseThrow(()-> new IdNotFoundException()))
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdAt(notice.getCreatedAt())
                .view(notice.getView())
                .build();
        return dto;
    }


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

}
