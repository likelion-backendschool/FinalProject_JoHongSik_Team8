package com.example.demo.cart.entity;

import com.example.demo.book.entity.Book;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    private String userNickName;
    private int count;
    private boolean isOrdered;

}
