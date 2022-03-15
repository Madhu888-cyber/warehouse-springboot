package com.sathyatech.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.sathyatech.app.model.Uom;

public interface IUomService {
	public Long saveUom(Uom uom);
	
	public List<Uom> getAllUom();
	
	public Boolean isExists(Long uomId);
	
	public void deleteUom(Long uomId);
	
	public Uom getOneUomById(Long uomId);
	
	public void updateUom(Uom uom);
	
	public Page<Uom> findAll(Pageable pageable);
	
	public Boolean isUomModelAndUomTypeExist(String uomModel,String uomType);
	
	public void saveFile(List<Uom> uomList);
	
	//Specifaction
	public Page<Uom> findAll(Specification<Uom> spec,Pageable pageable);
	
	//Find By UomModel
	public Uom findByUomModel(String uomModel);
	
}
