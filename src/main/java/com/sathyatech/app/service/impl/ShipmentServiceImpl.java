package com.sathyatech.app.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sathyatech.app.model.ShipmentType;
import com.sathyatech.app.repo.ShipmentRepository;
import com.sathyatech.app.service.IShipmentService;

@Service
public class ShipmentServiceImpl implements IShipmentService {

	@Autowired
	private ShipmentRepository repo;

	public Long saveShipmentType(ShipmentType shipmentType) {
		if(shipmentType.getEnableShipment()==null){
			shipmentType.setEnableShipment("NO");
		}
		shipmentType = repo.save(shipmentType);
		return shipmentType.getShipmentId();
	}

	public ShipmentType getOneShipmentTypeById(Long shipmentId) {
		ShipmentType shipmentType = repo.getOne(shipmentId);
		return shipmentType;
	}

	public void deleteShipmentTypeById(Long shipmentId) {
		repo.delete(shipmentId);
	}

	public List<ShipmentType> getAllShipmentType() {
		List<ShipmentType> listShipmentType= repo.findAll();
		Collections.sort(listShipmentType, new Comparator<ShipmentType>() {
			public int compare(ShipmentType o1, ShipmentType o2) {
				return o1.getShipmentId().compareTo(o2.getShipmentId());
			}
		});
		return listShipmentType;
	}
	
	public Boolean isShipmentIdExist(Long shipmentId) {
		return repo.exists(shipmentId);
	}
	
	public boolean isShipmentModeAndCodeExist(String shipmentMode, String shipmentCode) {
		long count =  repo.countShipmentModeAndCode(shipmentMode, shipmentCode);
		if (count==0)
		    return false;
		else 
			return true;
	}

	public void updateShipmentType(ShipmentType shipmentType) {
		shipmentType.setCreatedOn(repo.getOne(shipmentType.getShipmentId()).getCreatedOn());
		if(shipmentType.getEnableShipment()==null){
			shipmentType.setEnableShipment("NO");
		}
		repo.save(shipmentType);
	}
	
	/*PagiNation*/
	public Page<ShipmentType> findAll(Pageable pageable) {
		Page<ShipmentType> page = repo.findAll(pageable);
		return page;
	}
	
	//Save Excel File Data List of Data
	public void save(List<ShipmentType> shipmentList) {		
		repo.save(shipmentList);
	}
	
	//Search
	public Page<ShipmentType> findAll(Specification<ShipmentType> spec,
			Pageable pageable) {
		return repo.findAll(spec, pageable);
	}
	
	public List<ShipmentType> findByEnableShipment(String enable) {
		return repo.findByEnableShipment(enable);
	}

	public ShipmentType findByShipmentCode(String shipmentCode) {
		return repo.findByShipmentCode(shipmentCode);
	}
}
