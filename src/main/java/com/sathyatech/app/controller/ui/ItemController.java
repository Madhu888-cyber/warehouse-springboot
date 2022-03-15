package com.sathyatech.app.controller.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sathyatech.app.model.Item;
import com.sathyatech.app.service.IItemService;
import com.sathyatech.app.spec.ItemSpecification;
import com.sathyatech.app.util.ItemUtil;
import com.sathyatech.app.validator.ItemValidator;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemUtil util;
	
	@Autowired
	private IItemService service;
	
	@Autowired
	private ItemValidator validator;

	//1 Show Register
	@GetMapping("register")
	public String show(ModelMap map){
		map.addAttribute("item", new Item());
		util.addUiComponents(map);
		return "ItemRegister";
	}
	
	//1 Save Item
	@PostMapping("/register")
	public String saveItem(@ModelAttribute("item")Item item,Errors errors,ModelMap map){
		//Do validation
		validator.validate(item,errors);
		System.out.println(errors.getAllErrors());
		
		if(!errors.hasErrors()){
			Long itemId = service.saveItem(item);
			map.addAttribute("item", new Item());
			map.addAttribute("message", "Successfully Saved With Id:"+itemId);
		}
		
		util.addUiComponents(map);
		return "ItemRegister";
	}

	//Get All
	@GetMapping("/viewAll")
	public String getAll(@PageableDefault(size=3,sort="itemId",direction=Direction.ASC)Pageable pageable,@ModelAttribute("item")Item item,ModelMap map){
		ItemSpecification spec = new ItemSpecification(item);
		Page<Item> page = service.findAll(spec,pageable);
		map.addAttribute("page", page);
		return "ItemData";
	}
	
	//Delete
	@GetMapping("/delete")
	public String deleteItem(@RequestParam Long itemId){
		if(service.isExist(itemId)){
			service.deleteItem(itemId);
		}
		
		return "redirect:viewAll";
	}
	
	//Edit
	@GetMapping("/edit")
	public String editItem(@RequestParam Long itemId,ModelMap map){
		Item item = service.getOneItem(itemId);
		util.addUiComponents(map);
		map.addAttribute("item", item);
		return "ItemDataEdit";
	}
	
	//Update
	@PostMapping("/update")
	public String updateItem(@ModelAttribute("item")Item item,Errors errors,ModelMap map){
		//Do Validation
		validator.validate(item, errors);
		System.out.println(errors.getAllErrors());
		if(!errors.hasErrors()){
			service.updateItem(item);
			return "redirect:viewAll";
		}
		util.addUiComponents(map);
		return "ItemDataEdit";
	}
	
	//View One User
	@GetMapping("/view")
	public String viewPage(@RequestParam Long itemId,ModelMap map){
		Item item = service.getOneItem(itemId);
		map.addAttribute("item", item);
		return "ItemDataView";
	}
}