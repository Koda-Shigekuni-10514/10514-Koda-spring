package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			
			categoryList = categoryRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
			
			model.addAttribute("categoryList", categoryList);
			
			return "category";
		}
		
		// カテゴリー追加画面と処理
		@GetMapping("/category/add")
		public String showAddCategory() {
			return "addCategory";
		}

		
		@PostMapping("/category/add")
		public String addCategory(
				@RequestParam(name="name",defaultValue = "") String name,
				Model model
				) {
			
//				1.idがnullのオブジェクトを作成
				Category category = new Category(name);
//				2.リポジトリ.save(オブジェクト)を実行してデータを登録
				categoryRepository.save(category);

			return "redirect:/category";
		}
		
		// カテゴリー名更新画面表示と処理
		@GetMapping("/category/{id}/edit")
		public String edit(
			@PathVariable("id") Integer id,
			Model model
		){
			Optional<Category> dbdata = categoryRepository.findById(id);
			if(dbdata.isEmpty()){
				return "redirect:/category";
			}else{
				Category category = dbdata.get();
				model.addAttribute("category", category);
			}

			return "editCategory";
		}

		@PostMapping("/category/{id}/edit")
		public String editCategory(
			@PathVariable("id") Integer id,
			@RequestParam(name="name",defaultValue = "")String name
		){
			Optional<Category> categoryOption = categoryRepository.findById(id);
			if(!categoryOption.isEmpty()){
				Category category = categoryOption.get();
				category.setName(name);
				categoryRepository.save(category);
			}

			return "redirect:/category";
		}

		@PostMapping("/category/{id}/delete")
		public String deleteCategory(
			@PathVariable("id") Integer id
		){
			Optional<Category> cOptional = categoryRepository.findById(id);
			if(!cOptional.isEmpty()){
				categoryRepository.deleteById(id);
			}
			return "redirect:/category";
		}
}
