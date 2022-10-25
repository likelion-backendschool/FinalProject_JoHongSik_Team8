package com.example.demo.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String addCart(@Validated  CartDto cartDto , BindingResult bindingResult, Model model, Principal principal){
        if(bindingResult.hasErrors()){
            return "product/product_list";
        }

        cartService.addCart(cartDto,principal);
        return "redirect:/product/detail/%d".formatted(cartDto.getBookId());
    }


}
