package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.model.Item;

@Controller
public class CartController {

	
	@Autowired
	HttpSession session;
	
    @Autowired
    private Cart cart;

    @Autowired
    private Account account;
    
    

    @GetMapping("/cart")
    public String showCart(Model model) {

        model.addAttribute("items", cart.getItemList());
        return "cart";
    }

    
    @GetMapping({"/cart/login","/cart/logout"})
    public String index() {
    	
    	session.invalidate();
    	
    	return "cartLogin";
    }

    @PostMapping("/cart/add")
    public String addCart(
            @RequestParam String name,
            @RequestParam Integer price,
            Model model
    ) {

        cart.getItemList().add(new Item(name, price));

        model.addAttribute("items", cart.getItemList());
        return "cart";
    }
    
    @GetMapping("/cart/clear")
    public String clearCart() {
        cart.getItemList().clear();

        return "cart";
    }
    
    @PostMapping("/cart/login")
    public String login(
    		@RequestParam(name = "name") String name,
    		Model model
    		) {
    	
    	account.setName(name);
    	return "cart";
    }
}
