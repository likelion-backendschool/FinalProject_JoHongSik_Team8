package com.example.demo.articleAndContent;

import com.example.demo.articleAndContent.entity.ArticleAndContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ACRepository extends JpaRepository<ArticleAndContent,Long> {
}
