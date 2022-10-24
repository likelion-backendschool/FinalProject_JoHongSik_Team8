package com.example.demo.book;

import com.example.demo.article.entity.Article;
import com.example.demo.book.entity.Book;
import com.example.demo.book.entity.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String showCreateProduct(ProductDto productDto, Model model, Principal principal) {

        boolean prohibit = bookService.prohibitMember(principal.getName());

        if (prohibit) {
            return "redirect:/product/list";
        }

        List<Article> articleList = bookService.getAllArticle();
        model.addAttribute("articles", articleList);
        return "product/product_create";
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String save(@Validated ProductDto productDto, BindingResult bindingResult, Model model,Principal principal) {

        if (bindingResult.hasErrors()) {
            List<Article> articleList = bookService.getAllArticle();
            model.addAttribute("articles", articleList);
            return "product/product_create";
        }

        Long id = bookService.save(productDto, principal);
        return "redirect:/product/detail/%d".formatted(id);
    }

    @GetMapping("/detail/{id}")
    @PreAuthorize("isAuthenticated()")
    public String save(@PathVariable Long id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book",book);
        return "product/product_detail";
    }

}
