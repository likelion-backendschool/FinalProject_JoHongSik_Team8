package com.example.demo.article;


import com.example.demo.article.dto.ArticleDto;
import com.example.demo.article.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("new")
    public String showCreateArticle(ArticleDto articledto) {
        return "article/article_create";
    }

    @PostMapping("new")
    public String save(@Validated ArticleDto articledto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article/article_create";
        }

        try {
            articleService.save(articledto);
        } catch (Exception e) {
            bindingResult.rejectValue("article", "duplicated", "이미 있는 해시태그입니다.");
            return "article/article_create";
        }

        return "redirect:/article/list";

    }

    @GetMapping("list")
    public String showArticleList(Model model) {
        List<Article> articleList = articleService.findAll();
        model.addAttribute("articles",articleList);
        return "article/article_list";
    }


}
