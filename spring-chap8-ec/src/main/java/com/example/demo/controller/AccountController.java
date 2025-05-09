package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    Account account;

    @GetMapping({"/","/login","/logout"})
    public String index(){
        httpSession.invalidate();
        return "login";
    }


    @PostMapping("/login")
    public String login(
        @RequestParam(name="name") String name,
        Model model
    ){

        if(name == null || name.isEmpty()){
            model.addAttribute("message", "名前は入力必須です");
            return "login";
        }

        account.setName(name);
        return "redirect:items";
    }





}
