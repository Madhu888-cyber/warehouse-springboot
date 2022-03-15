package com.sathyatech.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.sathyatech.app.model.Item;

public interface IItemService {
	//Save
	public Long saveItem(Item item);
	
	//Get All Items
	public Page<Item> findAll(Pageable pageable);
	
	public List<Item> findAll();
	
	public Page<Item> findAll(Specification<Item> spec,Pageable pageable);
	
	//Delete
	public void deleteItem(Long itemId);
	
	//isExist
	public boolean isExist(Long itemId);
	
	//Get One By Id
	public Item getOneItem(Long itemId);
	
	//Update Item
	public void updateItem(Item item);
	
	//Multipart
	public void saveFile(List<Item> items);
	
	public List<Item> findItemsByVendor(Long vendorId);
	
	public List<Item> findItemByCustomer(Long id);
	
}
