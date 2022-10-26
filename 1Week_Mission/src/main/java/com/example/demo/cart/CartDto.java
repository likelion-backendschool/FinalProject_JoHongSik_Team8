package com.example.demo.cart;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CartDto {

    @NotEmpty
    private String subject;

    private Long bookId;

    private  String price;
}
