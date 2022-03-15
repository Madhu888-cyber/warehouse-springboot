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
import com.sathyatech.app.model.Uom;
import com.sathyatech.app.service.IUomService;
import com.sathyatech.app.validator.UomValidator;
import io.swagger.annotations.ApiImplicitParam;

@RestController
@RequestMapping("/rest/uom")
public class UomRestController {

	@Autowired
	private IUomService service;

	@Autowired
	private UomValidator validator;

	private Object response = null;

	/**
	 * Save Uom
	 * @param uom
	 * @param errors
	 * @return
	 */
	@PostMapping("/save")
	public ResponseEntity<Object> saveUom(@RequestBody Uom uom,Errors errors){
		//Before Saving Do Validations
		validator.setEditMode(false);
		validator.validate(uom, errors);
		if(errors.hasErrors()){
			return ResponseEntity.badRequest().body(errors.getAllErrors());
		}
		else{			
			Long uomId = service.saveUom(uom);
			return ResponseEntity.ok("Uom Created With Id:"+uomId);
		}
	}

	/**
	 * Get All Uom Records
	 * @return
	 */
	@GetMapping("/getAll")
	@ApiImplicitParam(name="page",dataType="String",paramType="query",value="Results Page You Want To Retrieve 0 to N",required=true)
	public ResponseEntity<Object> findAll(@PageableDefault(size=3,sort="uomId",direction=Direction.ASC) Pageable pageable){
		Page<Uom> page = service.findAll(pageable);
		//System.out.println(pageable.getPageNumber()+"   "+page.getTotalPages());

		if(page == null || pageable.getPageNumber() >= page.getTotalPages()){
			response = "No Page or Records Exists";
		}
		else{
			response = page; 
		}
		return ResponseEntity.ok(response);
	}

	/**
	 * Delete Uom using UomId
	 * @param uomId
	 * @return
	 */
	@DeleteMapping("/delete/{uomId}")
	public ResponseEntity<Object> deleteUomById(@PathVariable Long uomId){
		Boolean exist = service.isExists(uomId);
		if(!exist){
			response = "Uom with uomId:"+uomId+" Not Exist";
		}
		else{
			service.deleteUom(uomId);
			response = "UomId:"+uomId+" Deleted Successfully";
		}
		return ResponseEntity.ok(response);
	}

	/**
	 * Get One Uom using UomId
	 * @param uomId
	 * @return
	 */
	@GetMapping("/getOne/{uomId}")
	public ResponseEntity<Object> getOneUom(@PathVariable Long uomId){
		if(!service.isExists(uomId)){
			response = "Uom with uomId:"+uomId+" Not Exist";
		}
		else{
			Uom uom = service.getOneUomById(uomId);
			response = uom;
		}
		return ResponseEntity.ok(response);
	}

	/**
	 * Update Uom
	 * @param uom
	 * @param errors
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody Uom uom,Errors errors){
		Boolean exist = service.isExists(uom.getUomId());
		validator.setEditMode(true);
		validator.validate(uom, errors);
		if(errors.hasErrors()){
			response = ResponseEntity.badRequest().body(errors.getAllErrors());
		}
		else if(!exist){
			response = "UomId:"+uom.getUomId()+" Not Exist";
		}
		else{
			service.updateUom(uom);
			response = "Uom Updated Successfully With Id:"+uom.getUomId();
		}
		return ResponseEntity.ok(response);
	}
}