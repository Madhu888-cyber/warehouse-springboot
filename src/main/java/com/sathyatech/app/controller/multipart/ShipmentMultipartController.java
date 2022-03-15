package com.sathyatech.app.controller.multipart;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sathyatech.app.model.ShipmentType;
import com.sathyatech.app.service.IShipmentService;
import com.sathyatech.app.util.ShipmentTypeUtils;
import com.sathyatech.app.validator.ShipmentMultipartValidator;
import com.sathyatech.app.view.ShipmentTypeXlsxView;



@Controller
@RequestMapping("/multipart/shipments")
public class ShipmentMultipartController {
	
	@Autowired
	private IShipmentService service;
	
	@Autowired
	private ShipmentTypeUtils util;
	
	@Autowired
	private ShipmentMultipartValidator validator;
	
	//Show Upload Page
	@RequestMapping("/show")
	public String showUpload(){
		return "ShipmentMultipart";
	}
	
	//Save List Of Data
	@RequestMapping("/save")
	public String saveFile(@RequestParam("shipmentFile")MultipartFile shipmentFile,ModelMap map){
		if(shipmentFile == null || !shipmentFile.getOriginalFilename().contains(".xlsx")){
			map.addAttribute("message", "Invalid File");
		}
		else{
			//Convert MultipartFile to List
			List<ShipmentType> listShip= util.processMutilpart(shipmentFile);
			
			if(listShip.isEmpty()){
				map.addAttribute("message", "Rows Not Found Plz Check Sheet Name");
			}
			else{
				//Do Validation On List
				Map<String, List<String>> errorMap= validator.doValidation(listShip);
				
				if(errorMap.isEmpty()){
					service.save(listShip);
					map.addAttribute("message", "Records Saved Successfully..");
				}
				else {
					map.addAttribute("errorMap", errorMap);
				}
			}					
		}
		return "ShipmentMultipart";
	}
	
	//DownLoad
	@GetMapping("/download")
	public ModelAndView downlad(){
		List<ShipmentType> shipmentTypes= service.getAllShipmentType();
		return new ModelAndView(new ShipmentTypeXlsxView(),"shipmentTypes",shipmentTypes);
	}
}