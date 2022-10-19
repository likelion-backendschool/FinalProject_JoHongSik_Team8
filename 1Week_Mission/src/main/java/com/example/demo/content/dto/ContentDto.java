package com.example.demo.content.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ContentDto {

    @NotEmpty(message = "제목은 필수 입니다.")
    private String subject;

    @NotEmpty(message = "내용은 필수 입니다.")
    private String content;

    @NotEmpty(message = "해시태그는 필수 입니다.")
    private String articleName;
}
