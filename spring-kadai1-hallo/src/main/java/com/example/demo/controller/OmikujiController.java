package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OmikujiController {
	
	
	@GetMapping("/omikuji") //GETリクエストを受け取るURLを指定
	public String index() {
		

//		omikuji.htmlを表示させる
		return "omikuji";
	}
	
	@PostMapping("/omikuji")
	public String omikuji(
			Model model
			) {
		
		int rand = (int)(Math.random() * 6);
		
	switch(rand) {
	
	case 0:
		model.addAttribute("kuzi","大吉");
		break;
	case 1:
		model.addAttribute("kuzi","小吉");
		break;
	case 2:
		model.addAttribute("kuzi","凶");
		break;
	case 3:
		model.addAttribute("kuzi","吉");
		break;
	case 4:
		model.addAttribute("kuzi","吉");
		break;
	case 5:
		model.addAttribute("kuzi","吉");
		break;
	 default:
		 break;
	}
		
		
		
		return "omikuji";
	}
}
