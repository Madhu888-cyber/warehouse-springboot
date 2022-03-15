package com.sathyatech.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sathyatech.app.model.User;
import com.sathyatech.app.repo.AdminRepository;
import com.sathyatech.app.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminRepository repo;
	
	public User findByUserEmail(String email) {
		return repo.findByUserEmail(email);
	}
}
