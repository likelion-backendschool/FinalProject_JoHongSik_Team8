package com.example.demo.cart;

import com.example.demo.book.BookRepository;
import com.example.demo.book.BookService;
import com.example.demo.book.entity.Book;
import com.example.demo.cart.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CartService {
    private final BookService bookService;
    private final CartRepository cartRepository;
    public void addCart(CartDto cartDto, Principal principal) {
        Book book = bookService.findBookById(cartDto.getBookId());
        Cart cart = Cart.builder()
                .book(book)
                .count(1)
                .isOrdered(false)
                .userNickName(principal.getName())
                .build();

        cartRepository.save(cart);

    }
}
