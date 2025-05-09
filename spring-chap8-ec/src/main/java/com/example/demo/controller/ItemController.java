package com.example.demo.controller;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.CategoryRepository;
// import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
		

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;


	@GetMapping("/items")
    public String index(
        @RequestParam(name="categoryId",defaultValue = "") Integer categoryId,
        Model model
    ){

        // List<Item> itemList = new ArrayList<>();
        // itemList = itemRepository.findAll();


        model.addAttribute("categoryList", categoryRepository.findAll());

        if(categoryId != null){
            model.addAttribute("itemList", itemRepository.findByCategoryId(categoryId));
        }else{
            model.addAttribute("itemList", itemRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
        }


        return "items";
    }
}
