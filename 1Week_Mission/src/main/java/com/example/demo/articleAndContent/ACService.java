package com.example.demo.articleAndContent;

import com.example.demo.article.ArticleService;
import com.example.demo.article.entity.Article;
import com.example.demo.articleAndContent.entity.ArticleAndContent;
import com.example.demo.content.ContentService;
import com.example.demo.content.entity.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ACService {

    private final ContentService contentService;
    private final ArticleService articleService;
    private final ACRepository acRepository;

    public void save(Long id, String articleName) {
        Article article = articleService.findById(articleName);
        Content content = contentService.findByContentId(id);
        acRepository.save(
                ArticleAndContent.builder()
                        .article(article)
                        .content(content)
                        .build()
        );
    }
}
