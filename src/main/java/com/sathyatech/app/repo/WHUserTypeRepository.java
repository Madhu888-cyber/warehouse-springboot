package com.sathyatech.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sathyatech.app.model.WHUserType;

public interface WHUserTypeRepository extends JpaRepository<WHUserType, Long>,JpaSpecificationExecutor<WHUserType>{
	
	public List<WHUserType> findByUserType(String userType);
	
	//Fetch WhUserType based userCode
	public List<WHUserType> findByWhUserCodeIn(List<String> userCode);
		
	public WHUserType findByWhUserCode(String userCode);
	
}
