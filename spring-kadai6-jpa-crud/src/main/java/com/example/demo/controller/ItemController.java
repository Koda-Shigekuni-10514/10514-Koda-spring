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

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
		
		
		
		@Autowired
		ItemRepository itemRepository;
		
	
		//一覧画面(Items)
		@GetMapping("/items")
		public String index(
				Model model) {
			
			
			List<Item> itemList = new ArrayList<Item>();
			

			itemList  =  itemRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
			
			model.addAttribute("itemList" , itemList);
			
			return "items";
		}
		
		
		//商品追加画面を出す
		@GetMapping("/items/add")
		public String crete() {
			return "addItem";
		}
		
		//登録ボタンを押したときitemテーブルにデータを登録する
		@PostMapping("/items/add")
		public String store(
				@RequestParam(name="categoryId",defaultValue = "") Integer categoryId,
				@RequestParam(name="name",defaultValue = "") String name,
				@RequestParam(name="price",defaultValue = "") Integer price
				) {
			
//			1.idがnullのオブジェクトを作成
			Item item = new Item(categoryId,name,price);
//			2.リポジトリ.save(オブジェクト)を実行してデータを登録
			itemRepository.save(item);
			
			return "redirect:/items";
		}
		
		@GetMapping("/items/{id}/edit")
		public String edit(
				@PathVariable(name="id") Integer id,
				Model model
				) {
			
			//更新対象のデータを取得
			Optional<Item> dbData = itemRepository.findById(id);
			//取得できなかった場合商品一覧に戻る
			if(dbData.isEmpty()) {
				return "redirect:/items";
			}
			
			//取得したデータをHTMLで扱えるようにする
			Item item = dbData.get();
			
			model.addAttribute("item",item);
			//更新画面を開く
			return "editItem";
		}
		
		//更新画面で更新をする処理
		@PostMapping("/items/{id}/edit")
		public String update(
				@PathVariable("id") Integer id,
				@RequestParam(name="categoryId",defaultValue = "") Integer categoryId,
				@RequestParam(name="name",defaultValue = "") String name,
				@RequestParam(name="price",defaultValue = "") Integer price
				) {
				
			//更新対象のデータを取得
			Optional<Item> itemOption = itemRepository.findById(id);
			
			if(!itemOption.isEmpty()) {
				Item item = itemOption.get();
				
				item.setCategoryId(categoryId);
				item.setName(name);
				item.setPrice(price);
				
				itemRepository.save(item);
			}
			
		return "redirect:/items";		
		}
		
		//削除をするときの処理
		@PostMapping("/items/{id}/delete")
		public String delete(
				@PathVariable(name = "id") Integer id
				) {
			
			//消去対象のデータを取得
			Optional<Item> itemOption = itemRepository.findById(id);
			
			if(!itemOption.isEmpty()) {
				itemRepository.deleteById(id);
			}
			
			return "redirect:/items";	
		}
}