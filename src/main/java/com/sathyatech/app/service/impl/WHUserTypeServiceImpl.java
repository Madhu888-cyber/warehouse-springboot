package com.sathyatech.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sathyatech.app.model.WHUserType;
import com.sathyatech.app.repo.WHUserTypeRepository;
import com.sathyatech.app.service.IWHUserTypeService;
@Service
public class WHUserTypeServiceImpl implements IWHUserTypeService {

	@Autowired
	private WHUserTypeRepository repo;
	
	//Save
	public Long save(WHUserType whUserType) {
		return repo.save(whUserType).getWhUserId();
	}
	//Get All Pagination
	public Page<WHUserType> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}
	//View All Using Specification & Pagination 
	public Page<WHUserType> findAll(Specification<WHUserType> spec,Pageable pageable) {
		return repo.findAll(spec,pageable);
	}
	
	//is Exist
	public boolean isExist(Long whUserId) {
		return repo.exists(whUserId);
	}
	
	//Delete
	public void delete(Long whUserId) {
		repo.delete(whUserId);
	}
	
	//Get One WHUserType By Id
	public WHUserType findOneUser(Long whUserId) {
		WHUserType whUserType = repo.findOne(whUserId);
		return whUserType;
	}
	
	//Update 
	public void update(WHUserType whUserType) {
		repo.save(whUserType);
	}
	
	//Save File
	public void saveFile(List<WHUserType> whUserTypes) {
		repo.save(whUserTypes);
	}
	
	//Download 
	public List<WHUserType> findAll() {
		return repo.findAll();
	}
	
	//Find By UserType
	public List<WHUserType> findByUserType(String userType) {
		return repo.findByUserType(userType);
	}
	
	public List<WHUserType> findByWhUserCodeIn(List<String> userCode) {
		return repo.findByWhUserCodeIn(userCode);
	}
	

	public WHUserType findByWhUserCode(String userCode) {
		return repo.findByWhUserCode(userCode);
	}
}
