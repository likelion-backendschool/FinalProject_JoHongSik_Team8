package com.example.demo.articleAndContent;

import com.example.demo.article.entity.Article;
import com.example.demo.articleAndContent.entity.ArticleAndContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ACRepository extends JpaRepository<ArticleAndContent,Long> {
    List<ArticleAndContent> findAllByArticle(Article article);
}
