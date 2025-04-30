package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ContactController {
	
	@GetMapping("/contact")
	public String index() {
		
		return "contactForm";
	}
	
	@PostMapping("/contact")
	public String contact(
			@RequestParam(name="name") String name,
			@RequestParam(name="email") String email,
			Model model
			){
		
		List<String> errorList = new ArrayList<String>();
		String nameError = "名前は入力必須です";
		String lengthError = "名前は20字以内で入力してください";
		String mailError = "メールアドレスは必須です";
		if(name == null || name.isEmpty()) {
		 errorList.add(nameError);
		}
		else if(name.length() >= 20) {
			errorList.add(lengthError);
		}
		
		if(email == null || email.isEmpty()) {
			errorList.add(mailError);
		}
		
		if(errorList.size() > 0) {
			model.addAttribute("errorList",errorList);
			return "contactForm";
		}
		

		model.addAttribute("info","以下の内容にて受け付けました");
		model.addAttribute("name",name);
		model.addAttribute("email",email);
		
		return "contactResult";
	}
	
}

