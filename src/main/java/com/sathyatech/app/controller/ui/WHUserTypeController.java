package com.sathyatech.app.controller.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.sathyatech.app.model.WHUserType;
import com.sathyatech.app.service.IWHUserTypeService;
import com.sathyatech.app.spec.WHUserTypeSpecification;
import com.sathyatech.app.util.WHUserTypeUtil;
import com.sathyatech.app.validator.WHUserTypeValidator;
import com.sathyatech.app.view.WHUserTypeXlsxView;

@Controller("wHuserType")
@RequestMapping("/users")
public class WHUserTypeController {

	@Autowired
	private IWHUserTypeService service;
	
	@Autowired
	private WHUserTypeValidator validator;
	
	//Show WHUserTypeRegister
	@GetMapping("/register")
	public String show(ModelMap map){
		WHUserType whUserType = new WHUserType();
		//whUserType.setUserType("VENDOR");
		map.addAttribute("whUserType", whUserType);
		map.addAttribute("userTypes", WHUserTypeUtil.getUserTypes());
		map.addAttribute("whUserCodes", WHUserTypeUtil.getUserCode());
		map.addAttribute("whUserIdTypes", WHUserTypeUtil.getWHUserIdTypes());
		return "WHUserTypeRegister";
	}
	
	//1 Save 
	@PostMapping("/register")
	public String save(@ModelAttribute("whUserType") WHUserType whUserType, Errors errors,ModelMap map){
		//Do Validation
		validator.validate(whUserType, errors);
		//Check For Errors
		if(!errors.hasErrors()){
			Long userId = service.save(whUserType);
			map.addAttribute("whUserType", new WHUserType());
			map.addAttribute("message", "Successfully Created With Id: "+userId);
		}
		map.addAttribute("userTypes", WHUserTypeUtil.getUserTypes());
		map.addAttribute("whUserCodes", WHUserTypeUtil.getUserCode());
		map.addAttribute("whUserIdTypes", WHUserTypeUtil.getWHUserIdTypes());
		return "WHUserTypeRegister";
	}
	
	//View All
	@GetMapping("/viewAll")
	public String findAll(@PageableDefault(size=3,sort="whUserId",direction=Direction.ASC)Pageable pageable,
						  @ModelAttribute("whUserType") WHUserType whUserType,ModelMap map){
		WHUserTypeSpecification spec = new WHUserTypeSpecification(whUserType);
		Page<WHUserType> page= service.findAll(spec,pageable);
		map.addAttribute("userTypes", WHUserTypeUtil.getUserTypes());
		map.addAttribute("whUserIdTypes", WHUserTypeUtil.getWHUserIdTypes());
		map.addAttribute("page", page);
		return "WHUserTypeData";
	}
	
	//Delete
	@GetMapping("/delete")
	public String delete(@RequestParam Long whUserId){
		if(service.isExist(whUserId)){
			service.delete(whUserId);
		}
		return "redirect:viewAll";
	}
	
	//Get One By Id
	@GetMapping("/edit")
	public String edit(@RequestParam Long whUserId,ModelMap map){
		if(service.isExist(whUserId)){
			WHUserType whUserType= service.findOneUser(whUserId);

			map.addAttribute("whUserType", whUserType);
		}
		map.addAttribute("userTypes", WHUserTypeUtil.getUserTypes());
		map.addAttribute("whUserIdTypes", WHUserTypeUtil.getWHUserIdTypes());
		return "WHUserTypeDataEdit";
	}
	
	//Update
	@PostMapping("/update")
	public String update(@ModelAttribute("whUserType") WHUserType whUserType,Errors errors,ModelMap map){
		String page = "";
		validator.validate(whUserType, errors);
		if(errors.hasErrors()){
			map.addAttribute("userTypes", WHUserTypeUtil.getUserTypes());
			map.addAttribute("whUserIdTypes", WHUserTypeUtil.getWHUserIdTypes());
			page = "WHUserTypeDataEdit";
		}
		else{
			
			service.update(whUserType);
			page="redirect:viewAll";
		}
		return page;
	}
	
	//View Page
	@GetMapping("view")
	public String view(@RequestParam Long whUserId, ModelMap map){
		WHUserType whUserType= service.findOneUser(whUserId);
		map.addAttribute("whUserType", whUserType);
		return "WHUserTypeDataView";
	}
	
	//Download Page
	@GetMapping("/download")
	public ModelAndView getAll(@PageableDefault(size=3,sort="whUserId",direction=Direction.ASC)Pageable pageable){
		Page<WHUserType> page= service.findAll(pageable);
		return new ModelAndView(new WHUserTypeXlsxView(), "whUserTypes", page.getContent());
	}
}