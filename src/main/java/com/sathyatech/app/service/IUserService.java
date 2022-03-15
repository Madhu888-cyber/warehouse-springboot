package com.sathyatech.app.service;

import java.util.List;

import com.sathyatech.app.model.User;

public interface IUserService {
	public void saveUser(User user);
	public User findUserByEmail(String email);
	public List<User> findAll();
	public User findOne(Long userId);
	public void save(User user);
}
