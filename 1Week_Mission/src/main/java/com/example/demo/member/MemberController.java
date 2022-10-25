package com.example.demo.member;


import com.example.demo.cart.entity.Cart;
import com.example.demo.mail.MailService;
import com.example.demo.member.dto.MemberAddDto;
import com.example.demo.member.dto.MemberModifyDto;
import com.example.demo.member.dto.MemberSignUpDto;
import com.example.demo.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MailService mailService;
    private final MemberService memberService;
    @GetMapping("/login")
    public String showLogin(){
        return "member/member_login";
    }

    @GetMapping("/signup")
    public String showSignUp(MemberSignUpDto memberSignUpDto){
        return "member/member_signup";
    }

    @PostMapping("/signup")
    public String save(@Validated MemberSignUpDto memberSignUpDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "member/member_signup";
        }else if (!(memberSignUpDto.getPassword().equals(memberSignUpDto.getPassword2()))) {
            bindingResult.rejectValue("password2","passwordInCorrect","패스워드가 일치하지 않습니다.");
            return "member/member_signup";
        }

        boolean validEmail = memberService.checkEmailValidation(memberSignUpDto.getEmail());

        if(validEmail){
            bindingResult.rejectValue("email","duplicated","이미 존재하는 이메일입니다.");
            return "member/member_signup";
        }
        try {
            memberService.save(memberSignUpDto);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("duplicated","이미 등록된 아이디입니다.");
            return "member/member_signup";
        }catch (Exception e){
            return "member/member_signup";
        }
        return "member/member_login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify")
    public String showModify(Principal principal, Model model){
        MemberModifyDto dto = memberService.getMemberModifyDto(principal.getName());
        model.addAttribute("memberModifyDto",dto);

        return "member/member_modify";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify")
    public String modify(@Validated MemberModifyDto memberModifyDto,BindingResult bindingResult , Principal principal){
        if(bindingResult.hasErrors()){
            return "member/member_modify";
        }

        boolean validEmail = memberService.checkEmailValidation(memberModifyDto.getEmail());
        boolean validNickname = memberService.checkNicknameValidation(memberModifyDto.getNickname());

        if(validNickname){
            bindingResult.rejectValue("nickname","duplicated","이미 존재하는 닉네임입니다.");
            return "member/member_modify";
        }
        if(validEmail){
            bindingResult.rejectValue("email","duplicated","이미 존재하는 이메일입니다.");
            return "member/member_modify";
        }
        memberService.modify(memberModifyDto, principal);
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String showProfile(Principal principal, Model model,MemberAddDto memberAddDto){
        Member member = memberService.findByUsername(principal.getName());
        model.addAttribute("member",member);

        return "member/member_profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile")
    public String updateNickname(@Validated MemberAddDto memberAddDto, BindingResult bindingResult , Principal principal, Model model){
        if(bindingResult.hasErrors()){
            Member member = memberService.findByUsername(principal.getName());
            model.addAttribute("member",member);
            return"member/member_profile";
        }
        boolean validNickname = memberService.checkNicknameValidation(memberAddDto.getNickname());
        if(validNickname){
            bindingResult.rejectValue("nickname","duplicated","이미 존재하는 닉네임입니다.");
            return "member/member_profile";
        }
        memberService.updateNickname(principal.getName(),memberAddDto.getNickname());

        return "redirect:/member/profile";
    }

    @GetMapping("/findId")
    public String showFindId(){
        return "member/member_finId";
    }

    @PostMapping("/findId")
    @ResponseBody
    public String getId(@RequestParam("memail") String email){
        Member member = memberService.find(email);
        return member.getUsername();
    }

    @PostMapping("/mailConfirm")
    @ResponseBody
    String mailConfirm(@RequestParam("email") String email) throws Exception {

        String code = mailService.sendCertifyMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
    }

    @GetMapping("/cart")
    @PreAuthorize("isAuthenticated()")
    String showMyCart(Principal principal,Model model) throws Exception {
        List<Cart> carts = memberService.getCartByUsername(principal.getName());
        model.addAttribute("carts",carts);
        return "member/member_cart";
    }

}

