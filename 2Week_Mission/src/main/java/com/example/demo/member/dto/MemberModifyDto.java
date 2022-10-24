package com.example.demo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberModifyDto {

    @NotEmpty(message = "필명은 필수입니다.")
    private String nickname;
    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

}
