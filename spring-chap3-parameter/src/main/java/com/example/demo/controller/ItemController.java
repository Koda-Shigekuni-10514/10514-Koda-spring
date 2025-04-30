package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {
		
		@GetMapping("/")
		public String index() {
			return "itemForm";
		}
		
		@PostMapping("/item")
		public String confirm(
				@RequestParam(name = "name",defaultValue = "未設定") String name,
				@RequestParam(name = "price",defaultValue = "100") Integer price,
				//ラジオボタンの受け取り→name="genre"のvalueを値として受け取る
				@RequestParam(name="genre") Integer genre,
				//チェックボックスの選択した値の受け取り→name="color"　の選択されたvalueの値を配列として受け取る
				@RequestParam(name="color") List<String> colorList,
				Model model
				) {
			
			model.addAttribute("name",name);
			model.addAttribute("price",price);
			model.addAttribute("genre",genre);
			model.addAttribute("colorList",colorList);
			
			return "itemConfirm";
		}
		
		
//		/item/パラメータ名で宣言
		@GetMapping("/item/{id}") //GetMappingとPathVariableの変数は必ず一致させる
		public String show(
				//パスパラメータを受け取る：name="パラメータ名"で受け取るパラメータを指定
				@PathVariable("id") int id,
				Model model
				){
			
			switch(id) {
			
			case 101:
				model.addAttribute("name", "ボールペン");
				model.addAttribute("price", 100);
				break;
			case 102:
				model.addAttribute("name", "消しゴム");
				model.addAttribute("price", 50);
				break;
			case 103:
				model.addAttribute("name", "必須アモト酸");
				model.addAttribute("price", 21000000);
				model.addAttribute("genre", 3);
				break;
			default:
				model.addAttribute("name", "未設定");
				model.addAttribute("price", 0);
				break;
			}
			
			return "itemConfirm";
		}
		
}
