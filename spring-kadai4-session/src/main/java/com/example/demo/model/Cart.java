package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Cart {

    // 商品一覧をセッションで管理できるようにする
    private List<Item> itemList = new ArrayList<>();

    // デフォルトコンストラクタ
    public Cart() {

    }

    // getter/setter
    public List<Item> getItemList() {
        return itemList;
    }

    

    
    // setterは使わない
//  public void setItemList(List<Item> itemList) {
//      this.itemList = itemList;
//  }

}
