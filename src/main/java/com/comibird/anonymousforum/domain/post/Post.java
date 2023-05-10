package com.comibird.anonymousforum.domain.post;

import com.comibird.anonymousforum.domain.auditing.BaseTimeEntity;
import com.comibird.anonymousforum.service.PostResponseDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    // 생성자에 @Builder 적용
    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Entity -> DTO
    public PostResponseDTO toDTO() {
        return PostResponseDTO.builder()
                .id(id)
                .title(title)
                .content(content)
                .createdAt(getCreatedAt())
                .build();
    }
}