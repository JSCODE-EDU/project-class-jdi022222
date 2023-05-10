package com.comibird.anonymousforum.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDTO {

    private Long id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    public PostResponseDTO(Long id, String title, String content, LocalDateTime createdAt){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
