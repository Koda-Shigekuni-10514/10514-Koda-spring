package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Item;

@Component
@SessionScope
public class Cart {

    private List<Item> itemList = new ArrayList<>();

	public Cart() {
	}

	public List<Item> getItemList() {
		return itemList;
	}

    public void add(Item item){

        Item exItem = null;
        if(item == null){
            return;
        }

        for(Item items : itemList){
            if(items.getId() == item.getId()){
                exItem = items;
            }
        }
        if(exItem != null){
            exItem.setQuantity(exItem.getQuantity() + 1);

        }else{
            item.setQuantity(1);
            itemList.add(item);
        }
    }


    public int getTotalPrice(){

        Integer total = 0;

        for(Item items:itemList){
            total += items.getPrice() * items.getQuantity();
        }
        return total;
    }



    public void delete(int itemId){
        for(Item item : itemList){
            if(item.getId() == itemId){
                itemList.remove(item);
                break;
            }
        }
    }









}
