package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity                            //Entityクラスであることを宣言、DB接続に使えるようになる
@Table(name="items")   //接続するテーブル名を指定
public class Item {
//	create table public.items (
//			  id serial not null
//			  , category_id integer
//			  , name text
//			  , price integer
//			);
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)  //DBのテーブルの主キーが自動連番の場合に必要になるアノテーション
		private Integer id;                        //主キーであることを宣言
		
		@Column(name="category_id")  //接続対象の項目名を指定
		private Integer categoryId;
		
		@Column(name="name")
		private String name;
		
		@Column(name="price")
		private Integer price;
		
//		デフォルトコンストラクタ
		public Item() {
		}
		
//	　　id以外のフィールドをすべて持ったコンストラクタ(新規登録用)
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
