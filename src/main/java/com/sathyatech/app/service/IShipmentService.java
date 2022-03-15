package com.sathyatech.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.sathyatech.app.model.ShipmentType;

public interface IShipmentService {
	public Long saveShipmentType(ShipmentType shipmentType);
	
	public List<ShipmentType> getAllShipmentType();
	
	public void deleteShipmentTypeById(Long shipmentId);
	
	public ShipmentType getOneShipmentTypeById(Long shipmentId);
	
	public Boolean isShipmentIdExist(Long shipmentId);
	
	public boolean isShipmentModeAndCodeExist(String shipmentMode,String shipmentCode);
	
	public void updateShipmentType(ShipmentType shipmentType);
	
	public Page<ShipmentType> findAll(Pageable pageable);
	
	public void save(List<ShipmentType> shipmentList);
	
	//Search
	public Page<ShipmentType> findAll(Specification<ShipmentType> spec,Pageable pageable);
	
	public List<ShipmentType> findByEnableShipment(String enable);
	
	public ShipmentType findByShipmentCode(String shipmentCode);
}
