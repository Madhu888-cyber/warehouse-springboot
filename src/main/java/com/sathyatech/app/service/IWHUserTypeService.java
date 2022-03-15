package com.sathyatech.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.sathyatech.app.model.WHUserType;

public interface IWHUserTypeService {
	
	public Long save(WHUserType whUserType);
	
	public Page<WHUserType> findAll(Pageable pageable);
	
	public Page<WHUserType> findAll(Specification<WHUserType> spec,Pageable pageable);
	
	public List<WHUserType> findAll();
	
	public boolean isExist(Long whUserId);
	
	public void delete(Long whUserId);
	
	public WHUserType findOneUser(Long whUserId);
	
	public void update(WHUserType whUserType);
	
	public void saveFile(List<WHUserType> whUserTypes);
	
	public List<WHUserType> findByUserType(String userType);
	
	public List<WHUserType> findByWhUserCodeIn(List<String> userCode);
	
	public WHUserType findByWhUserCode(String userCode);
}
