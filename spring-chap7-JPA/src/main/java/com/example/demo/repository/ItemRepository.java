package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
		
	
	//@Query(value="SQL文",nativeQuery = ture)
	
	@Query(value="" + " SELECT * FROM items WHERE name LIKE ?1", nativeQuery = true)
	List<Item> findBykeyword(String keyword);
}
