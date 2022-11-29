package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    //현재 실행 중인 포트 넘버
    @LocalServerPort
    private int port;

    //필드 주입
    @Autowired
    private TestRestTemplate restTemplate;

    //필드 주입
    @Autowired
    private PostsRepository postsRepository;

    //테스트 메소드 종료마다 DB 정리
    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    //게시글 등록 테스트
    @Test
    public void Posts_등록된다() throws Exception {
        //given
        String title = "title";
        String content = "content";
        //요청 파라미터들 셋팅
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        //컨트롤러 테스트를 위한 url 변수 선언
        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        //해당 url로 파라미터를 요청한 POST 요청
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        //responseEntity를 통한 검증
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //DB 전체 필드 조회를 통한 검증
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    //게시글 갱신 테스트
    @Test
    public void Posts_수정된다() throws Exception {
        //given
        //각 속성 값 임의 지정
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2"; //테스트용 title
        String expectedContent = "content2"; //테스트용 content

        //갱신용 DTO에 테스트용 title과 content 저장
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        //컨트롤러 테스트를 위한 url 변수 선언
        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        //갱신용 DTO를 통해 requestEntity를 얻음
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        //responseEntity를 얻기 위해 TestRestTemplate의 exchange 호출
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        //responseEntity를 통한 검증
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        //DB 전체 필드 조회를 통한 검증
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}