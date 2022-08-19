package com.fastcampus.biz.service;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.persistence.CategoryRepository;
import com.fastcampus.biz.persistence.PostRepository;
import com.fastcampus.web.dto.BlogDto;
import com.fastcampus.biz.persistence.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    // 블로그 검색
    @Transactional
    public List<Blog> blogList(BlogDto blogDto) {
        if (blogDto.getSearchCondition().equals("TITLE")) {
            return blogRepository.findByTitleContaining(blogDto.getSearchKeyword());
        } else if (blogDto.getSearchCondition().equals("TAG")) {
            return blogRepository.findByTagContaining(blogDto.getSearchKeyword());
        }
        return null;
    }

    // 블로그 등록
    @Transactional
    public void insertBlog(Blog blog, Category category, HttpSession session) {
        blog.setBlogId((Long) session.getAttribute("blogId"));
        blog.setStatus("운영중");
        blog.setTag("No Tags");
        blogRepository.save(blog);

        // 미분류 카테고리 추가
        category.setBlogId(blog.getBlogId());
        category.setCategoryName("미분류");
        category.setDescription("기본으로 제공되는 카테고리입니다.");
        category.setDisplayType("TITLE");
        categoryRepository.save(category);
    }

    // 블로그 수정
    @Transactional
    public void updateBlog(Long blogId, BlogDto blogDto) {
        Blog blog = blogRepository.findById(blogId).orElseThrow();
        blog.setTitle(blogDto.getTitle());
        blog.setTag(blogDto.getTag());
    }

    // 블로그 삭제
    @Transactional
    public void deleteBlog(Long blogId) {
        blogRepository.deleteById(blogId);
        categoryRepository.deleteByBlogId(blogId);
        postRepository.deleteByBlogId(blogId);
    }

    // 블로그 조회
    @Transactional
    public Blog getBlog(Long blogId) {
        return blogRepository.findById(blogId).orElseThrow();
    }


    // 블로그 상태 변경
    @Transactional
    public void changeStatus(Long blogId) {
        Blog blog = getBlog(blogId);
        if (blog.getStatus().equals("운영중")) {
            blog.setStatus("삭제요청");
        }
    }
}
