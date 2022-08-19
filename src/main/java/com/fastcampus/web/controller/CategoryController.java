package com.fastcampus.web.controller;

import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.service.BlogService;
import com.fastcampus.biz.service.CategoryService;
import com.fastcampus.web.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final BlogService blogService;

    // 카테고리 생성
    @PostMapping("/category/{blogId}/insertCategory")
    public String insertCategory(Category category) {
        categoryService.insertCategory(category);
        return "redirect:/category/getCategoryList/{blogId}";
    }

    // 카테고리 수정
    @PostMapping("/category/{blogId}/updateCategory/{categoryId}")
    public String updateCategory(@PathVariable Long categoryId, CategoryDto categoryDto) {
        categoryService.updateCategory(categoryId, categoryDto);
        return "redirect:/category/getCategoryList/{blogId}";
    }

    // 카테고리 삭제
    @GetMapping("/category/{blogId}/deleteCategory/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteByCategoryId(categoryId);
        return "redirect:/category/getCategoryList/{blogId}";
    }

    // 카테고리 페이지로 이동
    @GetMapping("/category/getCategoryList/{blogId}")
    public String categoryListView(@PathVariable Long blogId, Model model) {
        model.addAttribute("blog", blogService.getBlog(blogId));
        model.addAttribute("categoryList", categoryService.getCategoryList(blogId));
        return "category/getCategoryList";
    }

    // 카테고리 수정 페이지로 이동
    @GetMapping("/category/getCategoryList/{blogId}/updateCategory/{categoryId}")
    public String updateCategoryView(@PathVariable Long categoryId, @PathVariable Long blogId, Model model) {
        model.addAttribute("blog", blogService.getBlog(blogId));
        model.addAttribute("categoryList", categoryService.getCategoryList(blogId));
        model.addAttribute("category", categoryService.getCategory(categoryId));
        return "category/updateCategory";
    }

}
