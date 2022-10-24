package com.example.demo.content;

import com.example.demo.article.ArticleService;
import com.example.demo.article.entity.Article;
import com.example.demo.articleAndContent.ACService;
import com.example.demo.articleAndContent.entity.ArticleAndContent;
import com.example.demo.content.dto.ContentDto;
import com.example.demo.content.entity.Content;
import com.example.demo.member.MemberService;
import com.example.demo.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final MemberService memberService;
    private final ContentRepository contentRepository;

    private final ACService acService;

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
        Content content1 = contentRepository.findById(content.getId()).orElse(null);
        Article article = articleService.findById(contentDto.getArticleId());
        acService.save(content1,article);

        return content.getId();
    }

    public Content findByContentId(Long id) {
        return contentRepository.findById(id).orElse(null);
    }

    public List<Article> getAllArticle() {
        return articleService.findAll();
    }

    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    public List<Content> findAllContentById(String id) {
        Article article = articleService.findById(id);
        List<ArticleAndContent> ac = acService.findAllByArticle(article);
        List<Content> contentList = new ArrayList<>();

        for(ArticleAndContent a : ac){
            contentList.add(a.getContent());
            System.out.println(contentList);
        }
        return contentList;
    }
}
