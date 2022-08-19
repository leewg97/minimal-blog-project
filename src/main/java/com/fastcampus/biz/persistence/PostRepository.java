package com.fastcampus.biz.persistence;

import com.fastcampus.biz.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByBlogId(Long blogId);

    List<Post> findByCategoryId(Long categoryId);
    void deleteByBlogId(Long blogId);

}
