package com.example.demo.content;

import com.example.demo.article.ArticleService;
import com.example.demo.article.entity.Article;
import com.example.demo.content.dto.ContentDto;
import com.example.demo.content.entity.Content;
import com.example.demo.member.MemberService;
import com.example.demo.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final MemberService memberService;
    private final ContentRepository contentRepository;

    private final ArticleService articleService;

    public boolean checkAuthor(String name) {
        Member member = memberService.findByUsername(name);
        if (member.getNickname() == null) {
            return true;
        } else {
            return false;
        }
    }

    public Long save(ContentDto contentDto, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());

        Content content = Content.builder()
                            .member(member)
                            .subject(contentDto.getSubject())
                            .Content(contentDto.getContent())
                            .build();
        contentRepository.save(content);
        return content.getId();
    }

    public Content findByContentId(Long id) {
        return contentRepository.findById(id).orElse(null);
    }

    public List<Article> getAllArticle() {
        return articleService.findAll();
    }
}
