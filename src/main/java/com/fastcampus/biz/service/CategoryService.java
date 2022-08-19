package com.fastcampus.biz.service;

import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.persistence.CategoryRepository;
import com.fastcampus.web.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 생성
    @Transactional
    public void insertCategory(Category category) {
        categoryRepository.save(category);
    }

    // 카테고리 수정
    @Transactional
    public void updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category updateCategory = categoryRepository.findById(categoryId).orElseThrow();
        updateCategory.setCategoryName(categoryDto.getCategoryName());
        updateCategory.setDescription(categoryDto.getDescription());
        updateCategory.setDisplayType(categoryDto.getDisplayType());
    }

    // 카테고리 삭제
    @Transactional
    public void deleteByCategoryId(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    // 카테고리 조회
    @Transactional
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }

    // 카테고리 목록 조회
    @Transactional
    public List<Category> getCategoryList(Long blogId) {
        return categoryRepository.findByBlogId(blogId);
    }

}
