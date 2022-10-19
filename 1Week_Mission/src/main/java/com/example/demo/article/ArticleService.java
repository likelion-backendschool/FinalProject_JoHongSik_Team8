package com.example.demo.article;

import com.example.demo.article.dto.ArticleDto;
import com.example.demo.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    public void save(ArticleDto articledto) {
        Article article = articleRepository.findByName(articledto.getName());
        if(article != null){
            throw new RuntimeException("중복");
        }
        articleRepository.save(
                Article.builder()
                        .name(articledto.getName())
                        .build()
        );
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(String articleId) {
        return articleRepository.findByName(articleId);
    }
}
