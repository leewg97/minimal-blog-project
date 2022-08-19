package com.fastcampus.biz.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false)
    private Long blogId;

    private String categoryName;
    private String description;
    private String displayType;

}
