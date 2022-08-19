package com.fastcampus.web.dto;

import lombok.Data;

@Data
public class BlogDto {

    private String title;
    private String tag;

    // 검색 관련 변수
    private String searchCondition;
    private String searchKeyword;
}
