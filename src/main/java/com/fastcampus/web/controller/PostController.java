package com.fastcampus.web.controller;

import com.fastcampus.biz.service.BlogService;
import com.fastcampus.biz.service.CategoryService;
import com.fastcampus.biz.service.PostService;
import com.fastcampus.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final BlogService blogService;
    private final CategoryService categoryService;

    // 포스트 작성 페이지로 이동
    @GetMapping("/post/insertPost/{blogId}")
    public String insertPostView(@PathVariable Long blogId, Model model) {
        model.addAttribute("blog", blogService.getBlog(blogId));
        model.addAttribute("category", categoryService.getCategoryList(blogId));
        model.addAttribute("categoryList", categoryService.getCategoryList(blogId));
        return "post/insertPost";
    }

    // 포스트 작성
    @PostMapping("/post/insertPost/{blogId}")
    public String insertPost(PostDto postDto) {
        postService.insertPost(postDto);
        return "redirect:/blog/blogMain/{blogId}";
    }

    // 포스트 수정 페이지로 이동
    @GetMapping("/post/updatePost/{blogId}/{postId}")
    public String updatePostView(@PathVariable Long blogId, @PathVariable Long postId, Model model) {
        model.addAttribute("blog", blogService.getBlog(blogId));
        model.addAttribute("post", postService.getPost(postId));
        model.addAttribute("categoryList", categoryService.getCategoryList(blogId));
        return "post/getPost";
    }

    // 포스트 수정
    @PostMapping("/post/updatePost/{blogId}/{postId}")
    public String updatePost(@PathVariable Long postId, PostDto postDto) {
        postService.updatePost(postId, postDto);
        return "redirect:/blog/blogMain/{blogId}";
    }

    // 포스트 삭제
    @GetMapping("/post/deletePost/{blogId}/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/blog/blogMain/{blogId}";
    }
}
