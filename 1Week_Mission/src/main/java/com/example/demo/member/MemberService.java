package com.example.demo.member;


import com.example.demo.mail.MailService;
import com.example.demo.member.dto.MemberModifyDto;
import com.example.demo.member.dto.MemberSignUpDto;
import com.example.demo.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

import static com.example.demo.member.entity.MemberType.GENERAL;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    public void save(MemberSignUpDto memberSignUpDto) throws Exception {
        memberRepository.save(
                Member.builder()
                        .username(memberSignUpDto.getUsername())
                        .password(passwordEncoder.encode(memberSignUpDto.getPassword()))
                        .email(memberSignUpDto.getEmail())
                        .authLevel(3L)
                        .memberType(GENERAL)
                        .build());

        sendEmail(memberSignUpDto);
    }

    private void sendEmail(MemberSignUpDto memberSignUpDto) throws Exception {
        mailService.sendSimpleMessage(memberSignUpDto.getEmail());
    }

    public void modify(MemberModifyDto memberModifyDto, Principal principal) {
        Member member = memberRepository.findByUsername(principal.getName()).orElse(null);
        member.setEmail(memberModifyDto.getEmail());
        member.setNickname(memberModifyDto.getNickname());
        memberRepository.save(member);
    }

    public MemberModifyDto getMemberModifyDto(String name) {
        Member member = memberRepository.findByUsername(name).orElse(null);
        return MemberModifyDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }


    public boolean checkNicknameValidation(String nickname) {
        Member member = memberRepository.findByNickname(nickname);
        if(member == null){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkEmailValidation(String email) {
        Member member = memberRepository.findByEmail(email);
        if(member == null){
            return false;
        }else{
            return true;
        }
    }

    public Member find(String email) {
        Member member =  memberRepository.findByEmail(email);
        return member;
    }

    public Member findByUsername(String name) {
        return memberRepository.findByUsername(name).orElse(null);
    }

    public void updateNickname(String name, String nickname) {
        Member member = memberRepository.findByUsername(name).orElse(null);
        member.setNickname(nickname);
        memberRepository.save(member);
    }
}
