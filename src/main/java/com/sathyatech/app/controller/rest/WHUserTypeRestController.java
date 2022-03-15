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

import com.sathyatech.app.model.WHUserType;
import com.sathyatech.app.service.IWHUserTypeService;
import com.sathyatech.app.util.WHUserTypeUtil;
import com.sathyatech.app.validator.WHUserTypeValidator;

import io.swagger.annotations.ApiImplicitParam;

@RestController
@RequestMapping("/rest/users")
public class WHUserTypeRestController {
	
	@Autowired
	private IWHUserTypeService service;
	
	@Autowired
	private WHUserTypeValidator validator;
	
	private Object response;
	
	//1.  Save
	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody WHUserType whUserType,Errors errors){
		//Do Validation
		validator.validate(whUserType, errors);
		if(errors.hasErrors()){
			return ResponseEntity.badRequest().body(errors.getAllErrors());
		}
		else{
			whUserType.setWhUserCode(WHUserTypeUtil.getUserCode());
			Long whUserId= service.save(whUserType);
			return ResponseEntity.ok("User Created Successfully with ID: "+whUserId);
		}
	}
	
	//2. ViewAll
	@GetMapping("viewAll")
	@ApiImplicitParam(name="page",dataType="String",paramType="query",value="Results Page You Want To Retrieve 0 to N",required=true)
	public ResponseEntity<Object> viewAll(@PageableDefault(size=3,sort="whUserId",direction=Direction.ASC)Pageable pageable){
		
		Page<WHUserType> page=service.findAll(pageable);
		//System.out.println(pageable.getPageNumber());
		//System.out.println(page.getTotalPages());
		if(pageable== null || pageable.getPageNumber() >= page.getTotalPages()){
			response = "Page Not Found...";
		}
		else{
			response = page;
		}
		return ResponseEntity.ok(response);
	}
	
	//3. Delete 
	@DeleteMapping("/delete/{whUserId}")
	public ResponseEntity<Object> delete(@PathVariable("whUserId") Long whUserId){
		if(service.isExist(whUserId)){
			service.delete(whUserId);
			response = "User Deleted Successfully";
		}
		else{
			response = "user with "+whUserId+" Not Exist";
		}
		return ResponseEntity.ok(response);
	}
	
	//4. Update
	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody WHUserType whUserType,Errors errors){
		
		validator.validate(whUserType, errors);
		if(errors.hasErrors()){
			response = errors.getAllErrors();
			return ResponseEntity.badRequest().body(response);
		}
		else if(!service.isExist(whUserType.getWhUserId())){
			response = "UserId:"+whUserType.getWhUserId()+" Not Exist";
		}
		else {
			service.update(whUserType);
			response = "User with userId"+whUserType.getWhUserId()+" Updated";
		}
		return ResponseEntity.ok(response);
	}
}