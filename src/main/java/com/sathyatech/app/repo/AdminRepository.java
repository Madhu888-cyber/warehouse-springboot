package com.sathyatech.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sathyatech.app.model.User;


public interface AdminRepository extends JpaRepository<User, Long>{
	public User findByUserEmail(String email);
}
