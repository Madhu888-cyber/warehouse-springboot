package com.sathyatech.app.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.sathyatech.app.model.OrderMethod;
import com.sathyatech.app.service.IOrderMethodService;
import com.sathyatech.app.validator.OrderValidator;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/rest/order")
public class OrderRestController {

	@Autowired
	private IOrderMethodService service;

	@Autowired
	private OrderValidator validator;

	private Object response = null;
	//Save Order
	@PostMapping("/save")
	public ResponseEntity<Object> saveOrder(@RequestBody OrderMethod orderMethod,Errors errors){
		//Do Validations
		validator.setEditMode(false);
		validator.validate(orderMethod, errors);
		if(errors.hasErrors()){
			return ResponseEntity.badRequest().body(errors.getAllErrors());
		}
		else{
			Long orderId = service.saveOrderMethod(orderMethod);
			return ResponseEntity.ok("Order Save Successfully wi Id:"+orderId);
		}		
	}

	//Get All Order
	@GetMapping("/viewAll")
	@ApiImplicitParams({@ApiImplicitParam(name="page",dataType="String",paramType="query",value="Results page you want to retrieve 0..N",required=true),
		@ApiImplicitParam(name="size",dataType="String",paramType="query", value="Number of records per page",required=true)})
	public ResponseEntity<Object> getAllOrder(/*@PageableDefault(size=3,sort="orderId",direction=Direction.ASC)*/
			Pageable pageable){
		Page<OrderMethod> page = service.findAll(pageable);
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



	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<Object> delete(@PathVariable Long orderId){
		if(!service.isExist(orderId)) {
			response = "OrderMethod "+orderId+" not exist.";
		}else {
			service.deleteOrderMethod(orderId);
			response = "OrderMethod is deleted Id:"+orderId;
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody OrderMethod orderMethod,Errors errors){
		validator.setEditMode(true);
		validator.validate(orderMethod, errors);
		if(errors.hasErrors()) {
			response = errors.getAllErrors();
			return ResponseEntity.badRequest().body(response);
		}
		else {
			service.updateOrderMethod(orderMethod);
			response = "OrderMethod updated with Id:"+orderMethod.getOrderId();
			return ResponseEntity.ok(response);
		}
	}
}
