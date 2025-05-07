package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//create table public.categories (
//id serial not null
//, name text
//);



@Entity
@Table(name="categories")
public class Category {

		
		@Id
		private Integer id;
		
		@Column(name="name")
		private String name;
		
		
		public Category() {
		}


		public Category(Integer id, String name) {
			this.id = id;
			this.name = name;
		}


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}
		
		
	
}
