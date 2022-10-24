package com.example.demo.book;

import com.example.demo.article.ArticleService;
import com.example.demo.article.entity.Article;
import com.example.demo.book.entity.Book;
import com.example.demo.book.entity.ProductDto;
import com.example.demo.content.entity.Content;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberService;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final ArticleService articleService;
    private final MemberService memberService;
    private final BookRepository bookRepository;
    public List<Article> getAllArticle() {
        return articleService.findAll();
    }

    public boolean prohibitMember(String name) {
        Member member = memberService.findByUsername(name);
        if(member.getMemberType().equals(MemberType.GENERAL)){
            return true;
        }
        return false;
    }

    public Long save(ProductDto productDto, Principal principal) {
        Member member = memberService.findByUsername(principal.getName());
        Book book = Book.builder()
                        .subject(productDto.getSubject())
                        .member(member)
                        .price(String.valueOf(productDto.getPrice()))
                        .article(articleService.findById(productDto.getArticleId()))
                        .build();
        bookRepository.save(book);
        return book.getId();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
