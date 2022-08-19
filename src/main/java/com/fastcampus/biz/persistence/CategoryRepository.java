package com.fastcampus.biz.persistence;

import com.fastcampus.biz.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByBlogId(Long blogId);
    void deleteByBlogId(Long blogId);

}
