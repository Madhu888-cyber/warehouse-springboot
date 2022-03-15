package com.sathyatech.app.service.impl;



import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sathyatech.app.model.Uom;
import com.sathyatech.app.repo.UomRepository;
import com.sathyatech.app.service.IUomService;


@Service
public class UomServiceImpl implements IUomService {
	
	@Autowired
	private UomRepository repo;
	
	public Long saveUom(Uom uom) {
		repo.save(uom);
		return uom.getUomId();
	}
	
	public List<Uom> getAllUom() {
		List<Uom> listUom= repo.findAll();
		Collections.sort(listUom, new Comparator<Uom>() {
			public int compare(Uom obj1, Uom obj2) {
				return obj1.getUomId().compareTo(obj2.getUomId());
			}
		});
		return listUom;
	}
	
	public Boolean isExists(Long uomId) {
		return repo.exists(uomId);
	}
	
	public void deleteUom(Long uomId) {
		repo.delete(uomId);
	}
	
	public Uom getOneUomById(Long uomId) {
		Uom uom = repo.getOne(uomId);
		return uom;
	}

	public void updateUom(Uom uom) {
		repo.save(uom);	
	}
	
	/*Pagination*/
	public Page<Uom> findAll(Pageable pageable) {
		return repo.findAll(pageable);	
	}
	//Check UomModel And UomType Already Exist Or Not
	public Boolean isUomModelAndUomTypeExist(String uomModel, String uomType) {
		Long count = repo.countUomModelAndType(uomModel, uomType);
		if(count==0)
			return false;
		else
			return true;
	}
	
	//To Perform Bulk Opeartions(Save File)
	public void saveFile(List<Uom> uomList) {
		repo.save(uomList);
	}
	
	//Specification + Pageable
	public Page<Uom> findAll(Specification<Uom> spec, Pageable pageable) {
		Page<Uom> page = repo.findAll(spec, pageable);
		return page;
	}
	
	public Uom findByUomModel(String uomModel) {
		return repo.findByUomModel(uomModel);
	}
}