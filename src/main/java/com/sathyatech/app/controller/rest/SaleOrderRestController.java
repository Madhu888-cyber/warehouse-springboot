package com.sathyatech.app.controller.rest;

import io.swagger.annotations.ApiImplicitParam;

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

import com.sathyatech.app.model.SaleOrder;
import com.sathyatech.app.service.ISaleOrderService;
import com.sathyatech.app.util.GeneratorsUtil;
import com.sathyatech.app.validator.SaleOrderValidator;

/**
 * @author Pratik
 *
 */
@RestController
@RequestMapping("/rest/sale")
public class SaleOrderRestController {

	@Autowired
	private ISaleOrderService service;
	
	@Autowired
	private SaleOrderValidator validator;
	
	private Object response = null;
	
	/**
	 * Save
	 * @param saleOrder
	 * @param errors
	 * @return
	 */
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody SaleOrder saleOrder,Errors errors){
		//Set SaleCode 
		saleOrder.setOrderCode(GeneratorsUtil.codeGenerator());
		
		validator.validate(saleOrder, errors);
		if(errors.hasErrors()){
			return ResponseEntity.badRequest().body(errors.getAllErrors());
		}
		else{
			Long saleOrdId= service.saveSale(saleOrder);
			return ResponseEntity.ok("Sale Order Created With Id: "+saleOrdId);
		}
	}
	
	/**
	 * GetAll
	 * @param pageable
	 * @return
	 */
	@GetMapping("/viewAll")
	@ApiImplicitParam(name="page",paramType="query",dataType="String",value="Enter Page Num From 0-N",required=true)
	public ResponseEntity<?> viewAll(@PageableDefault(size=3,sort="saleId",direction=Direction.ASC)Pageable pageable){
		Page<SaleOrder> page= service.findAll(pageable);
		//System.out.println(pageable.getPageNumber());
		//System.out.println(page.getNumber());
		if(pageable.getPageNumber() > page.getTotalPages()-1){
			response = "Entered Page Number is Not Available";
		}
		else{
			response = page;
		}
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Delete
	 * @param saleId
	 * @return
	 */
	@DeleteMapping("/delete/{saleId}")
	public ResponseEntity<?> delete(@PathVariable("saleId")Long saleId){
		SaleOrder saleOrder = service.findById(saleId);
		if(saleOrder != null){
			service.delete(saleId);
			response = "Sale Order with code\'"+saleOrder.getOrderCode()+"\' Deleted Successfully";
		}
		else{
			response = "Entered sale Id Not Exist..";
		}
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Update
	 * @param saleOrder
	 * @param errors
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody SaleOrder saleOrder,Errors errors){
		validator.validate(saleOrder, errors);
		if(errors.hasErrors()){
			return ResponseEntity.badRequest().body(errors.getAllErrors());
		}
		else{
			if(service.isExist(saleOrder.getSaleId())){
				SaleOrder slord= service.findById(saleOrder.getSaleId());
				
				saleOrder.setCreatedOn(slord.getCreatedOn());
				
				saleOrder.setOrderCode(slord.getOrderCode());
				
				service.update(saleOrder);
				
				response = "Sale Order with ID:"+saleOrder.getSaleId()+" updated..";
			}
			else{
				response = "Sale Order With given Id:"+saleOrder.getSaleId()+" is Not Exist..";
			}
		}
		return ResponseEntity.ok(response);
	}
}
