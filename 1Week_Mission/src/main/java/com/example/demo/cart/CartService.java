package com.example.demo.cart;

import com.example.demo.book.BookRepository;
import com.example.demo.book.BookService;
import com.example.demo.book.entity.Book;
import com.example.demo.cart.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final BookRepository bookRepository;
    private final CartRepository cartRepository;
    public void addCart(CartDto cartDto, Principal principal) {
        Book book = bookRepository.findById(cartDto.getBookId()).orElse(null);
        Cart cart = Cart.builder()
                .book(book)
                .count(1)
                .isOrdered(false)
                .userNickName(principal.getName())
                .build();

        cartRepository.save(cart);

    }

    public List<Cart> findAllByUserNickname(String name) {
        return cartRepository.findALlByUserNickName(name);
    }
}
