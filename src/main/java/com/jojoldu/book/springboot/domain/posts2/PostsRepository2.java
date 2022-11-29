package com.jojoldu.book.springboot.domain.posts2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostsRepository2 extends JpaRepository<Posts2,Long>{

    @Query("SELECT p FROM Posts2 p ORDER BY p.id DESC")
    List<Posts2> findAllDesc();
}
