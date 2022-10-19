package com.example.demo.base;

import com.example.demo.article.ArticleService;
import com.example.demo.articleAndContent.ACService;
import com.example.demo.content.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    private ArticleService articleService;
    private ContentService contentService;
    private ACService acService;

}
