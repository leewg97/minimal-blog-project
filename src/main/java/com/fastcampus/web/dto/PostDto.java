package com.fastcampus.web.dto;

import lombok.Data;

@Data
public class PostDto {

    private Long blogId;
    private Long categoryId;
    private String content;
    private String title;
}
