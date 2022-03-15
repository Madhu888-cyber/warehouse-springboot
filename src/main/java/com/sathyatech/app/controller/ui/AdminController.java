package com.sathyatech.app.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathyatech.app.model.User;
import com.sathyatech.app.service.IAdminService;
import com.sathyatech.app.service.IUserService;
import com.sathyatech.app.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IAdminService service;
		
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserServiceImpl serviceImpl;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public String findAll(ModelMap map){
		List<User> list=userService.findAll();
		map.addAttribute("list", list);
		map.addAttribute("size", list.size());
		System.out.println(list.size());
		return "AdminPage";
	}
	
	@GetMapping("/active")
	public String active(@RequestParam Long userId){
		serviceImpl.setIsActivate(false);
		User user= userService.findOne(userId);
		userService.save(user);
		return "redirect:all";
	}
	
	@GetMapping("/inactive")
	public String inActive(@RequestParam Long userId){
		serviceImpl.setIsActivate(true);
		User user= userService.findOne(userId);
		System.out.println(user.toString());
		userService.save(user);
		return "redirect:all";
	}
}
