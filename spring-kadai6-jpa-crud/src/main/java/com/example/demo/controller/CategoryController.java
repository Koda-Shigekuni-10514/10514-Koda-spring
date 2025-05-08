package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

//create table public.categories (
//		  id serial not null
//		  , name text
//		);


@Controller
public class CategoryController {
		
		
		@Autowired
		CategoryRepository categoryRepository;
		
		@GetMapping("/category")
		public String index(
				Model model
				) {
			
			List<Category> categoryList = new ArrayList<Category>();
			
			categoryList = categoryRepository.findAll();
			
			model.addAttribute("categoryList", categoryList);
			
			return "category";
		}
		
		@GetMapping("/category/add")
		public String showAddCategory() {
			return "addCategory";
		}
		
		@PostMapping("/category/add")
		public String addCategory(
				@RequestParam(name="name",defaultValue = "") String name,
				Model model
				) {
			
			if(name.isEmpty()) {
				model.addAttribute("error", "入力必須です");
				return "redirect:/category/add";
			}
			else {
//				1.idがnullのオブジェクトを作成
				Category category = new Category(name);
//				2.リポジトリ.save(オブジェクト)を実行してデータを登録
				categoryRepository.save(category);
				
			}
			
			
			return "redirect:/category";
		}
		
		
}
