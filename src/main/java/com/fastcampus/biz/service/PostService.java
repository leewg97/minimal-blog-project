package com.fastcampus.biz.service;

import com.fastcampus.biz.domain.Post;
import com.fastcampus.biz.persistence.CategoryRepository;
import com.fastcampus.biz.persistence.PostRepository;
import com.fastcampus.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 포스트 작성
    @Transactional
    public void insertPost(PostDto postDto) {
        Post post = new Post();
        post.setBlogId(postDto.getBlogId());
        post.setCategoryId(postDto.getCategoryId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        postRepository.save(post);
    }

    // 포스트 수정
    @Transactional
    public void updatePost(Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId).orElseThrow();
        post.setCategoryId(postDto.getCategoryId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
    }

    // 포스트 삭제
    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    // 포스트 목록
    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }

    // BlogId로 포스트 목록 검색
    public List<Post> getPostByBlogId(Long blogId) {
        return postRepository.findByBlogId(blogId);
    }

    // 카테고리 별 포스트 목록
    public List<Post> getPostByCategoryId(Long categoryId) {
        return postRepository.findByCategoryId(categoryId);
    }

}
