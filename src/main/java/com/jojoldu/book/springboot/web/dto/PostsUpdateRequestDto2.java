package com.jojoldu.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto2 {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto2(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
}
