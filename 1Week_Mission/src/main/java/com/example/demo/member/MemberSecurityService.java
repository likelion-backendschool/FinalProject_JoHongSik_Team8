package com.example.demo.member;

import com.example.demo.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> loginMember = memberRepository.findByUsername(username);

        if(loginMember.isEmpty()){
            throw new RuntimeException("찾을 수 없는 아이디입니다.");
        }

        Member owner = loginMember.get();
        List<GrantedAuthority> authorityList = new ArrayList<>();

        return new User(owner.getUsername(), owner.getPassword(), authorityList);
    }
}
