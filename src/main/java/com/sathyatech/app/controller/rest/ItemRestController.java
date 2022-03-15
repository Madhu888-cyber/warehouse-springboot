package com.sathyatech.app.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathyatech.app.model.Item;
import com.sathyatech.app.service.IItemService;
import com.sathyatech.app.validator.ItemValidator;

import io.swagger.annotations.ApiImplicitParam;

@RestController
@RequestMapping("/rest/item")
public class ItemRestController {
	
	@Autowired
	private IItemService itemService;
	
	@Autowired
	private ItemValidator validator;
	
	private Object response=null;
	
	//1 Save
	@PostMapping("/save")
	public ResponseEntity<Object> saveItem(@RequestBody Item item,Errors errors){
		//Do validation 
		validator.validate(item, errors);
		if(errors.hasErrors()){
			return ResponseEntity.badRequest().body(errors.getAllErrors());
		}
		else{
			Long itemId = itemService.saveItem(item);
			return ResponseEntity.ok("Item Saved Successfully with Id: "+itemId);
		}
	}
	
	
	//2 Get All
	@GetMapping("/viewAll")
	@ApiImplicitParam(name="page",dataType="String",paramType="query",value="Enter Page Number 0 to N",required=true)
	public ResponseEntity<Object> getAll(@PageableDefault(size=3,sort="itemId",direction=Direction.ASC) Pageable pageable){
		
		Page<Item> page= itemService.findAll(pageable);
		
		if(page != null ){
			
			System.out.println(page.getNumber());
			System.out.println(page.getTotalPages());
			if(page.getNumber() <= page.getTotalPages()-1){
				response = page;
			}
			else {
				response = "Page Num is Greater Than Total Pages";
			}
		}
		else{
			response = "Page is Empty";
		}
		return ResponseEntity.ok(response);
	}
	
	//Update
	@PutMapping("/update")
	public ResponseEntity<Object> updateItem(@RequestBody Item item,Errors errors){
		validator.validate(item, errors);
		if(errors.hasErrors()) {
			response = ResponseEntity.badRequest().body(errors.getAllErrors());
		}
		else if(!itemService.isExist(item.getItemId())){
			response = "ItemId "+item.getItemId()+" Not Exist";
		}
		else {
			itemService.updateItem(item);
			response = "OrderMethod updated with Id:"+item.getItemId();
		}
		return ResponseEntity.ok(response);
	}
	
	//Delete
	@DeleteMapping("/delete/{itemId}")
	public ResponseEntity<Object> deleteItem(@PathVariable Long itemId){
		if(!itemService.isExist(itemId)){
			response = "ItemId "+itemId+" Not Exist";
		}
		else {
			itemService.deleteItem(itemId);
			response = "ItemId "+itemId+" Deleted Successfully";
		}
		return ResponseEntity.ok(response);
	}
}