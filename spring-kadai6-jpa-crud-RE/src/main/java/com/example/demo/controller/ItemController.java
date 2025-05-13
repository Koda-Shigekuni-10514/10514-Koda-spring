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


    @GetMapping("/items")
    public String index(
        Model model
    ){
        List<Item> itemList = new ArrayList<>();
        itemList = itemRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("itemList", itemList);
        return "items";
    }

    @GetMapping("/items/add")
    public String create(){
        return "addItem";
    }

    @PostMapping("/items/add")
    public String store(
        @RequestParam(name="categoryId",defaultValue = "") Integer categoryId,
        @RequestParam(name="name", defaultValue = "") String name,
        @RequestParam(name="price", defaultValue = "") Integer price,
        Model model
    ){

        Item items = new Item(categoryId, name, price);
        itemRepository.save(items);
        return "redirect:/items";
    }

    @GetMapping("/items/{id}/edit")
    public String edit(
        @PathVariable("id") Integer itemId,
        Model model
    ){
        Optional<Item> dbdata = itemRepository.findById(itemId);
        if(dbdata.isEmpty()){
            return "redirect:/items";
        }

        Item item = dbdata.get();
        model.addAttribute("item", item);


        return "editItem";
    }

    @PostMapping("/items/{id}/edit")
    public String update(
        @PathVariable("id") Integer itemId,
        @RequestParam(name="categoryId",defaultValue = "") Integer categoryId,
        @RequestParam(name="name", defaultValue = "") String name,
        @RequestParam(name="price", defaultValue = "") Integer price,
        Model model
    ){
        Optional<Item> itemOp = itemRepository.findById(itemId);
        if(!itemOp.isEmpty()){
            Item items = itemOp.get();
            items.setCategoryId(categoryId);
            items.setName(name);
            items.setPrice(price);

            itemRepository.save(items);

        }

        return "redirect:/items";
    }

    @PostMapping("/items/{id}/delete")
    public String delete(
        @PathVariable("id") Integer id
    ){
        Optional<Item> itemOptional = itemRepository.findById(id);

        if(!itemOptional.isEmpty()){
            itemRepository.deleteById(id);
        }
        return "redirect:/items";
    }
}
