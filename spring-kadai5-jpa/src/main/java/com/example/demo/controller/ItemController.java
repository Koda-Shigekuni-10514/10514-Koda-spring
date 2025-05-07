package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
		
		
		
		@Autowired
		ItemRepository itemRepository;
		
		@Autowired
		CategoryRepository categoryRepository;
		
		
	
		@GetMapping("/items")
		public String index(
				  //検索系のパラメータはdefaultValueがないとエラーになってしまう
				@RequestParam(name="keyword", defaultValue = "") String keyword,
				@RequestParam(name="categoryId", defaultValue = "") Integer categoryId,
				@RequestParam(name="maxPrice",defaultValue = "") Integer maxPrice,
				@RequestParam(name="sort",defaultValue = "" ,required = false) String sort,
				Model model) {
			
			List<Category> categoryList = categoryRepository.findAll();
			
			model.addAttribute("categories",categoryList);
			
			
			
			List<Item> itemList = new ArrayList<Item>();
			
			
			itemList = itemRepository.findAll();
			
			if(maxPrice != null) {
				itemList = itemRepository.findBymaxPrice(maxPrice);
			}
			else if("priceAsc".equals(sort)) {
				itemList = itemRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
			}
			else if(categoryId != null) {
				itemList = itemRepository.findByCategoryId(categoryId);
			}
			
			

			
			model.addAttribute("itemList" , itemList);
			
			return "items";
		}
}
