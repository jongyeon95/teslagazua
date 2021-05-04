package com.jongyeon.teslagazua.model;

import com.jongyeon.teslagazua.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDto {

    private Long id;

    private Long userid;

    private String title;

    private String content;

    private Long view;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private User user;
}
