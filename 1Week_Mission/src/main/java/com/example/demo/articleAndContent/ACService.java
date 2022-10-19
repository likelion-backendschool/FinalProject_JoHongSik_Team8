package com.example.demo.articleAndContent;

import com.example.demo.article.ArticleService;
import com.example.demo.article.entity.Article;
import com.example.demo.articleAndContent.entity.ArticleAndContent;
import com.example.demo.content.ContentService;
import com.example.demo.content.entity.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ACService {

    private final ACRepository acRepository;


    public void save(Content content, Article article) {
        acRepository.save(
                ArticleAndContent.builder()
                        .article(article)
                        .content(content)
                        .build()
        );
    }

    public List<ArticleAndContent> findAllByArticle(Article article) {
            return acRepository.findAllByArticle(article);
    }
}
