package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts2.Posts2;
import lombok.Getter;


@Getter
public class PostsResponseDto2 {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto2(Posts2 entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
