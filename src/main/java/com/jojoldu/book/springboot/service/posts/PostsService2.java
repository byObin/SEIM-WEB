package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.domain.posts2.Posts2;
import com.jojoldu.book.springboot.domain.posts2.PostsRepository2;
import com.jojoldu.book.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService2 {
    private final PostsRepository2 postsRepository2;

    @Transactional
    public Long save(PostsSaveRequestDto2 requestDto2) {
        return postsRepository2.save(requestDto2.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto2 requestDto2) {
        Posts2 posts2 = postsRepository2.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts2.update(requestDto2.getTitle(), requestDto2.getContent());

        return id;
    }

    public PostsResponseDto2 findById (Long id) {
        Posts2 entity = postsRepository2.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto2(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto2> findAllDesc() {
        return postsRepository2.findAllDesc().stream()
                .map(PostsListResponseDto2::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts2 posts2 = postsRepository2.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository2.delete(posts2);
    }
}
