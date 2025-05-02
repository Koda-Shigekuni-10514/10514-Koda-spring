package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;

@Controller
public class BlogController {
		
		
		@Autowired
		HttpSession session;
		
		
		//@Autowired
		//使いたいファイル 処理で使う変数名;
		
		
		@Autowired
		Account account;
		
		
		
		@GetMapping({"/","/logout"})
		public String index() {
			
			session.invalidate();
			return "login";
		}
		
		
		@PostMapping("/login")
		public String login(
				@RequestParam(name = "name") String name,
				Model model
				) {
			
			account.setName(name);
			return  "blog";
		}
		
		
		@GetMapping("/blog")
		public String showBlog() {
			return "blog";
		}
		
//		@GetMapping("/logout")
//		public String logout() {
//			
//			session.invalidate();
//			return "login";
//		}
		
}
