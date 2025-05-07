package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
		
		
		
		@Autowired
		ItemRepository itemRepository;
		
	
	
		@GetMapping("/items")
		public String showItem(
				  //検索系のパラメータはdefaultValueがないとエラーになってしまう
				@RequestParam(name="keyword", defaultValue = "") String keyword,
				Model model) {
			
			
			List<Item> itemList = new ArrayList<Item>();
			
			if(keyword != null && keyword.length() > 0) {
				itemList = itemRepository.findBykeyword("%" + keyword + "%");
			}else {
				itemList  =  itemRepository.findAll();
			}
			
			
			
			model.addAttribute("itemList" , itemList);
			
			return "items";
		}
}
