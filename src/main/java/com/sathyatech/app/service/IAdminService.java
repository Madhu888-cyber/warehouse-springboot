package com.sathyatech.app.service;

import com.sathyatech.app.model.User;

public interface IAdminService {

	public User findByUserEmail(String email);
}
