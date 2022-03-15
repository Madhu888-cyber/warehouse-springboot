package com.sathyatech.app.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sathyatech.app.model.User;
import com.sathyatech.app.repo.UserRepository;
import com.sathyatech.app.service.IUserService;


@Service("userService")
public class UserServiceImpl implements IUserService{
	
	public Boolean isActivate;

	public void setIsActivate(Boolean isActivate) {
		this.isActivate = isActivate;
	}

	@Autowired
	private UserRepository userRepository;
	
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setIsAcctExp(0);
        user.setIsAcctLock(0);
        user.setIsPwdExp(0);
       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
			user.setUserCreatedOn(sdf.parse("2018-03-12 07:19:21.726"));
			user.setLastLogin(sdf.parse("2018-03-14 07:19:21.726"));
		} 
        catch (ParseException e) {
			System.out.println(e.getMessage());
		}*/
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		Collections.sort(users, new Comparator<User>() {
			public int compare(User o1, User o2) {
				return o1.getUserId().compareTo(o2.getUserId());
			}
		});
		return users;
	}
	
	@Override
	public User findOne(Long userId) {
		return userRepository.findOne(userId);
	}
	
	public void save(User user){
		if(isActivate){
			user.setActive(0);
		}
		else{
			user.setActive(1);
		}
		userRepository.save(user);
	}
}