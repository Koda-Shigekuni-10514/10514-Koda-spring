package com.example.demo.entity;
// -- 商品テーブル
// CREATE TABLE items
// (
//    id SERIAL PRIMARY KEY,
//    category_id INTEGER,
//    name TEXT,
//    price INTEGER
// );

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="items")
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="category_id")
    private Integer categoryId;
    @Column(name = "name")
    private String name;
    @Column(name= "price")
    private Integer price;
    
    public Item() {
    }

    public Item(Integer categoryId, String name, Integer price) {
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    

}
