package com.sathyatech.app.controller.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathyatech.app.model.Uom;
import com.sathyatech.app.service.IUomService;
import com.sathyatech.app.spec.UomSpecification;
import com.sathyatech.app.util.UomTypeUtil;
import com.sathyatech.app.validator.UomValidator;




@Controller
@RequestMapping("/uom")
public class UomController {
	
	@Autowired
	private IUomService service;
	
	@Autowired
	private UomValidator validator;
	
	/**
	 * Show Registration Page
	 * @param map
	 * @return
	 */
	@GetMapping("/register")
	public String showReg(ModelMap map){
		map.addAttribute("uom", new Uom());
		map.addAttribute("listType", UomTypeUtil.listType());
		return "UomRegister";
	}
	
	/**
	 * Save Uom
	 * @param uom
	 * @param map
	 * @return
	 */
	@PostMapping("/register")
	public String saveUom(Uom uom, Errors errors, ModelMap map){
		//Before Saving Do validations
		validator.setEditMode(false);
		validator.validate(uom, errors);
		if(!errors.hasErrors()){
			Long uomId = service.saveUom(uom);
			//Clean The For By Creating Empty Uom Obj & send To UI
			map.addAttribute("uom", new Uom());
			map.addAttribute("message", "Uom Created With Id:"+uomId);
		}
		map.addAttribute("listType", UomTypeUtil.listType());	//Show Uom Type
		return "UomRegister";
	}
	
	/**
	 * Show All Uom
	 * @param map
	 * @return
	 */
	@GetMapping("/allUom")
	public String findAll(@PageableDefault(size=3,sort="uomId",direction=Direction.ASC)Pageable pageable,ModelMap map){
		Page<Uom> page = service.findAll(pageable);
		map.addAttribute("page", page);
		return "UomData";
	}
	
	/**
	 * Delete Uom using UomId
	 * @param uomId
	 * @return
	 */
	@GetMapping("/delete")
	public String deleteUom(@RequestParam Long uomId){
		service.deleteUom(uomId);
		return "redirect:getAll";
	}
	
	@GetMapping("/update")
	public String getOneUom(@RequestParam Long uomId, ModelMap map){
		Uom uom = service.getOneUomById(uomId);
		map.addAttribute("listType", UomTypeUtil.listType());	//Show Uom Type
		map.addAttribute("uom", uom);
		return "UomUpdate";
	}
	/**
	 * Edit Uom
	 * @param uom
	 * @param errors
	 * @param map
	 * @return
	 */
	@PostMapping("/edit")
	public String edit(Uom uom,Errors errors,ModelMap map){
		//Before Updation Do Validation
		validator.setEditMode(true);
		validator.validate(uom, errors);
		String page = "";
		
		if(errors.hasErrors()){
			map.addAttribute("listType", UomTypeUtil.listType());
			page = "UomUpdate";
		}
		else{
			service.updateUom(uom);
			page = "redirect:getAll";
		}
		return page;
	}
	
	//Search Using Specification
	@GetMapping("/getAll")
	public String getAll(@PageableDefault(size=3,sort="uomId",direction=Direction.ASC)Pageable pageable,@ModelAttribute Uom uom,ModelMap map){
		Specification<Uom> spec= new UomSpecification(uom);
		Page<Uom> page = service.findAll(spec, pageable);		
		map.addAttribute("uom", uom);
		map.addAttribute("page", page);
		map.addAttribute("listType", UomTypeUtil.listType());
		return "UomData";
	}
}
