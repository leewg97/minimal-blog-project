package com.fastcampus.web.controller;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.service.CategoryService;
import com.fastcampus.biz.service.PostService;
import com.fastcampus.web.dto.BlogDto;
import com.fastcampus.biz.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final PostService postService;
    private final CategoryService categoryService;

    // 블로그 등록 화면 이동
    @GetMapping("/blog/insertBlog")
    public String insertBlogView() {
        return "blog/insertBlog";
    }

    // 블로그 등록
    @PostMapping("/blog/insertBlog")
    public String insertBlog(Blog blog, Category category, HttpSession session) {
        blogService.insertBlog(blog, category, session);
        return "redirect:/";
    }

    // 블로그 수정
    @PostMapping("/blog/updateBlog/{blogId}")
    public String updateBlog(@PathVariable Long blogId, BlogDto blogDto) {
        blogService.updateBlog(blogId, blogDto);
        return "redirect:/blog/blogMain/{blogId}";
    }

    // 블로그 삭제
    @GetMapping("/blog/deleteBlog/{blogId}")
    public String deleteBlog(@PathVariable Long blogId) {
        blogService.deleteBlog(blogId);
        return "redirect:/";
    }

    // 블로그 메인 화면 이동
    @GetMapping("/blog/blogMain/{blogId}")
    public String blogMainView(@PathVariable Long blogId, Model model) {
        model.addAttribute("blog", blogService.getBlog(blogId));
        model.addAttribute("posts", postService.getPostByBlogId(blogId));
        model.addAttribute("categoryList", categoryService.getCategoryList(blogId));
        return "blog/blogMain";
    }

    // 블로그 관리 화면 이동
    @GetMapping("/blog/getBlog/{blogId}")
    public String blogManageView(@PathVariable Long blogId, Model model) {
        model.addAttribute("blog", blogService.getBlog(blogId));
        return "blog/getBlog";
    }

    // 카테고리 별 포스트 검색
    @GetMapping("/blog/{blogId}/postByCategory/{categoryId}")
    public String getBlogPostByCategory(@PathVariable Long blogId, @PathVariable Long categoryId, Model model) {
        model.addAttribute("blog", blogService.getBlog(blogId));
        model.addAttribute("categoryList", categoryService.getCategoryList(blogId));
        model.addAttribute("posts", postService.getPostByCategoryId(categoryId));
        return "blog/blogMain";
    }

    // 블로그 삭제 요청
    @GetMapping("/blog/deleteRequest/{blogId}")
    public String deleteBlog(@PathVariable Long blogId, Model model) {
        model.addAttribute("blog", blogService.getBlog(blogId));
        return "blog/deleteRequest";
    }

    // 블로그 상태 변경
    @PostMapping("/blog/status/{blogId}")
    public String changeStatus(@PathVariable Long blogId) {
        blogService.changeStatus(blogId);
        return "redirect:/";
    }

}
