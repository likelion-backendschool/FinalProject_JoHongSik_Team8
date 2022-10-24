package com.example.demo.book.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ProductDto {

    @NotEmpty(message = "카테고리는 필수입니다.")
    private String articleId;

    @NotEmpty(message = "제목은 필수입니다.")
    private String subject;

    @NotEmpty(message = "가격은 필수입니다.")
    private String price;

}
