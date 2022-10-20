package com.example.demo.article.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ArticleDto {

    @NotEmpty(message = "입력해라")
    private String name;
}
