package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	@GetMapping("/") //GETリクエストを受け取るURLを指定
	public String index(
			Model model
			) {
		

//		hello.htmlを表示させる
		return "index";
	}
	
	@GetMapping("/input") //GETリクエストを受け取るURLを指定
	public String input(
			Model model
			) {
		
//		input.htmlを表示させる
		return "input";
	}
	
	
	@GetMapping("/hello") //GETリクエストを受け取るURLを指定
	public String hello(
			Model model
			) {
		
//		input.htmlを表示させる
		return "hello";
	}
	
	@PostMapping("/hello") //Postリクエストを受け取るURLを指定
	public String show(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "age") Integer age,
			@RequestParam(name = "hobby") String hobby,
			Model model
			) {
		
		

		
		model.addAttribute("name",name);
		model.addAttribute("age",age);
		model.addAttribute("hobby",hobby);
		
		if(age <= 18) {
			model.addAttribute("info","未成年です");
		}else {
			int x = age - 18;
			model.addAttribute("info","成年してから" + x + "年たちました");
		}
		
//		hello.htmlを表示させる
		return "hello";
	}
	
}
