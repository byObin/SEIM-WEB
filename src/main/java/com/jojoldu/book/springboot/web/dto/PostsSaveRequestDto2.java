package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts2.Posts2;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto2 {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto2(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts2 toEntity() {
        return Posts2.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}