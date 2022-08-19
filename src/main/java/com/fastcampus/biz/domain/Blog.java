package com.fastcampus.biz.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Blog {

    @Id
    @Column(nullable = false)
    private Long blogId;
    private String status;
    private String tag;
    private String title;

}
