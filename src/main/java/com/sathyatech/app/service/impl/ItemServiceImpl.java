package com.sathyatech.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sathyatech.app.model.Item;
import com.sathyatech.app.repo.ItemRepository;
import com.sathyatech.app.service.IItemService;

@Service
public class ItemServiceImpl implements IItemService {
	
	@Autowired
	private ItemRepository repo;
	//1. Save
	public Long saveItem(Item item) {
		return repo.save(item).getItemId();
	}
	
	//Get All Items
	public Page<Item> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	public List<Item> findAll() {
		return repo.findAll();
	}
	
	public Page<Item> findAll(Specification<Item> spec, Pageable pageable) {
		return repo.findAll(spec, pageable);
	}
	
	//Delete
	public void deleteItem(Long itemId) {
		repo.delete(itemId);
	}
	
	//isExist
	public boolean isExist(Long itemId) {
		return repo.exists(itemId);
	}
	
	//Get One By Id
	public Item getOneItem(Long itemId){
		return repo.findOne(itemId);		
	}
		
	//Update Item
	public void updateItem(Item item) {
		repo.save(item);
	}
	
	public void saveFile(List<Item> items) {
		repo.save(items);
	}
	
	public List<Item> findItemsByVendor(Long vendorId) {
		return repo.findItemsByVendor(vendorId);
	}
	
	@Override
	public List<Item> findItemByCustomer(Long id) {
		return repo.findItemByCustomer(id);
	}
}