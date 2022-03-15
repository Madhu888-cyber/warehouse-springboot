package com.sathyatech.app.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathyatech.app.model.ShipmentType;
import com.sathyatech.app.service.IShipmentService;
import com.sathyatech.app.util.ShipmentTypeUtils;
import com.sathyatech.app.validator.ShipmentValidator;

import io.swagger.annotations.ApiImplicitParam;



@RestController
@RequestMapping("/rest/shiptype")
public class ShipmentRestController {
	
	@Autowired
	private IShipmentService service;
	
	@Autowired
	private ShipmentValidator validator;
	
	private Object response;
	
	//1) Save
	@PostMapping("/save")
	public ResponseEntity<Object> saveShipment(@RequestBody ShipmentType shipmentType,Errors errors){
		//DO validation
		validator.validate(shipmentType, errors);
		if(errors.hasErrors()){
			return ResponseEntity.badRequest().body(errors.getAllErrors());
		}
		else{
			shipmentType.setShipmentCode(ShipmentTypeUtils.getShipmentCode());
			Long shipId = service.saveShipmentType(shipmentType);
			return ResponseEntity.ok("ShipmentType Saved Successfully Id:"+shipId);
		}
	}
	
	//2) ViewAll 
	@GetMapping("/viewAll")
	@ApiImplicitParam(name="page",dataType="String",paramType="query",value="Plz Provide Page No from 0...N",required=true)
	public ResponseEntity<Object> findAll(@PageableDefault(size=3,sort="shipmentId",direction=Direction.ASC)Pageable pageable){
		System.out.println(pageable.getPageNumber()+"---"+pageable.getPageSize());
		Page<ShipmentType> page = service.findAll(pageable);
		
		if(page != null){
			if(page.getNumber() <= page.getTotalPages()-1){
				response = page;
			}
			else {
				response = "Page Num is Greater Than Total Pages";
			}
		}
		else{
			response = "Page is Empty";
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody ShipmentType shipmentType,Errors errors){
		
		validator.validate(shipmentType, errors);
		if(errors.hasErrors()) {
			response = errors.getAllErrors();
			return ResponseEntity.badRequest().body(response);
		}
		else {
			service.updateShipmentType(shipmentType);
			response = "ShipmentType updated with Id:"+shipmentType.getShipmentId();
			return ResponseEntity.ok(response);
		}
	}
	
	// 4.Delete Record 
	@DeleteMapping("/delete/{shipmentId}")
	public ResponseEntity<Object> delete(@PathVariable Long shipmentId){
		
		if(!service.isShipmentIdExist(shipmentId)) {
			response = "ShipmentId not exist :"+shipmentId;
		}else {
			service.deleteShipmentTypeById(shipmentId);
			response = "ShipmentType deleted Successfully Id:"+shipmentId;
		}
		return ResponseEntity.ok(response);
	}
}
