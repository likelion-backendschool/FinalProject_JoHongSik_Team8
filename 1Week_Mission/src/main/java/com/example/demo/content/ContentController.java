package com.example.demo.content;

import com.example.demo.article.entity.Article;
import com.example.demo.content.dto.ContentDto;
import com.example.demo.content.entity.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {
        private final ContentService contentService;
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/new")
    public String showCreateContent(ContentDto contentDto,Model model){
        List<Article> articleList = contentService.getAllArticle();
        model.addAttribute("articles",articleList);
        return "content/content_create";
    }

    @PostMapping("/new")
    @PreAuthorize("isAuthenticated()")
    public String save(@Validated ContentDto contentDto, BindingResult bindingResult,Principal principal,Model model){
        if(bindingResult.hasErrors()){
            List<Article> articleList = contentService.getAllArticle();
            model.addAttribute("articles",articleList);
            return "content/content_create";
        }
        Long id = contentService.save(contentDto,principal);
        return "redirect:/content/detail/%d".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/detail/{id}")
    public String showCreateContent(@PathVariable Long id, Model model){
        Content content = contentService.findByContentId(id);
        model.addAttribute("content",content);
        return"content/content_detail";
    }

}
