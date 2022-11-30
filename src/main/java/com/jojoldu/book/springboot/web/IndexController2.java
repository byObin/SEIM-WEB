package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.service.posts.PostsService2;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController2 {

    private final PostsService2 postsService2;

    @GetMapping("/index2")
    public String index2(Model model) {
        model.addAttribute("posts2", postsService2.findAllDesc());
        return "index2";
    }

    @GetMapping("/posts2/save")
    public String postsSave2() {
        return "posts-save";
    }

    @GetMapping("/posts2/update/{id}")
    public String postsUpdate2(@PathVariable Long id, Model model) {
        PostsResponseDto2 dto2 = postsService2.findById(id);
        model.addAttribute("post2", dto2);

        return "posts-update2";
    }
}
