package com.example.demo.member.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberSignUpDto {

    @NotEmpty(message = "로그인 아이디는 필수입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
    private String password2;

    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;


    private String nickname;
}
