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

import com.sathyatech.app.model.ShipmentType;
import com.sathyatech.app.service.IShipmentService;
import com.sathyatech.app.spec.ShipmentSpecification;
import com.sathyatech.app.util.ShipmentTypeUtils;
import com.sathyatech.app.validator.ShipmentValidator;


@Controller
@RequestMapping("/shipment")
public class ShipmentController {

	@Autowired
	private IShipmentService service;

	@Autowired
	private ShipmentValidator validator;

	@GetMapping("/register")
	public String showReg(ModelMap map){
		map.addAttribute("shipmentType", new ShipmentType());
		map.addAttribute("listShipMode", ShipmentTypeUtils.listShipMode());
		map.addAttribute("listGrade", ShipmentTypeUtils.generateGrade());
		return "ShipmentRegister";
	}

	@PostMapping("/register")
	public String saveShipmentType(@ModelAttribute ShipmentType shipmentType,Errors errors,ModelMap map){
		//Do Validation Before Saving
		validator.setEditMode(false);
		validator.validate(shipmentType, errors);
		if(!errors.hasErrors()){
			Long shipmentId = service.saveShipmentType(shipmentType);
			map.addAttribute("shipmentType", new ShipmentType());
			map.addAttribute("shipmentId", "Save Successfully with ShipmentId="+shipmentId);
		}
		map.addAttribute("listShipMode", ShipmentTypeUtils.listShipMode());
		map.addAttribute("listGrade", ShipmentTypeUtils.generateGrade());
		return "ShipmentRegister";
	}

	/*//View All ShipmentType Data
	@GetMapping("/allShipmentType")
	public String getAllShipmentType(ModelMap map){
		List<ShipmentType> listShipment = service.getAllShipmentType();
		map.addAttribute("listShipment", listShipment);
		return "ShipmentData";
	}*/

	//Delete ShipmentType by ShipmentId
	@GetMapping("/delete")
	public String deleteShipmentTypeById(@RequestParam Long shipmentId){
		service.deleteShipmentTypeById(shipmentId);
		return "redirect:viewAll";
	}

	//Show Data In Update Page Using getOneShipmentTypeById()
	@GetMapping("/update")
	public String getOneShipmentTypeById(@RequestParam Long shipmentId,ModelMap map){
		ShipmentType shipmentType= service.getOneShipmentTypeById(shipmentId);
		map.addAttribute("listShipMode", ShipmentTypeUtils.listShipMode());
		map.addAttribute("listGrade", ShipmentTypeUtils.generateGrade());
		map.addAttribute("shipmentType", shipmentType);
		return "ShipmentUpdate";
	}

	//Update Data
	@PostMapping("/edit")
	public String updateShipmentType(@ModelAttribute ShipmentType shipmentType,Errors errors,ModelMap map){
		//DO Validation
		validator.setEditMode(true);
		validator.validate(shipmentType, errors);
		String page="";
		if(errors.hasErrors()){
			map.addAttribute("listShipMode", ShipmentTypeUtils.listShipMode());
			map.addAttribute("listGrade", ShipmentTypeUtils.generateGrade());
			map.addAttribute("shipmentType", shipmentType);
			page = "ShipmentUpdate";
		}
		else{
			service.updateShipmentType(shipmentType);
			page = "redirect:viewAll";
		}
		return page;
	}
	
	//View All ShipmentType Data With Pagination
	@GetMapping("/viewAll")
	public String findAll(@PageableDefault(size=3,sort="shipmentId",direction=Direction.ASC)Pageable pageable,
			@ModelAttribute("shipmentType")ShipmentType shipmentType,ModelMap map){
		ShipmentSpecification spec = new ShipmentSpecification(shipmentType);
		Page<ShipmentType> page = service.findAll(spec, pageable);
		map.addAttribute("page", page);
		map.addAttribute("shipmentModes", ShipmentTypeUtils.listShipMode());
		map.addAttribute("shipmentGrades", ShipmentTypeUtils.generateGrade());
		return "ShipmentData";
	}
}