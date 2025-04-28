package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //コントローラーを表すアノテーション(必ずつける！！)
public class HelloController {
	

//	戻り値：String
//	メソッド名：処理に即した名前
// 引数：処理によって変わる
	
	//「@GetMapping」はURLと対応させるための宣言
	//ホスト名:localhost:8080
	//[http://ホスト名/]にアクセスしたときの処理
	
	@GetMapping("/") //GETリクエストを受け取るURLを指定
	public String index(
			Model model
			) {
		
		model.addAttribute("name","田中");
//		hello.htmlを表示させる
		return "hello";
	}
	
//	localhost:8080/inputにアクセスしたときの処理
	@GetMapping("/input")
	public String input() {
		
		return "input";
	}
	
	// @PostMapping
	// 秘匿通信を使って、ユーザーからリクエストが投げられた時の処理
	// 大体のニ入力フォームはこれで送る
	@PostMapping("/input")
	public String inputName(
			@RequestParam(name = "name") String name,
			Model model
			){
		
		// 入力された「名前」を受け取る→引数で受け取る
		
		
		// 入力フォームに受け取った名前をhello.htmlで使えるように登録
		model.addAttribute("name", name);
		
		
		return "hello";
	}
	
}
