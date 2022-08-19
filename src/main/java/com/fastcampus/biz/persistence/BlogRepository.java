package com.fastcampus.biz.persistence;

import com.fastcampus.biz.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByTitleContaining(String searchKeyword);
    List<Blog> findByTagContaining(String searchKeyword);


}
