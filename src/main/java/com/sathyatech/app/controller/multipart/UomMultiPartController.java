package com.sathyatech.app.controller.multipart;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sathyatech.app.model.Uom;
import com.sathyatech.app.service.IUomService;
import com.sathyatech.app.util.UomTypeUtil;
import com.sathyatech.app.validator.UomMultipartValidator;
import com.sathyatech.app.view.UomXlsxView;


@Controller
@RequestMapping("/multipart/uoms")
public class UomMultiPartController {
	@Autowired
	private IUomService service;
	
	@Autowired
	private UomTypeUtil util;
	
	@Autowired
	private UomMultipartValidator validator;
	
	@RequestMapping("/show")
	public String showPage(){
		return "UomMultipart";
	}
	
	//Save Excel Data
	@RequestMapping("/uomImport")
	public String saveExcel(@RequestParam("uomFile") MultipartFile uomFile,ModelMap map){
		//Check File is Not null & Valid
		if(uomFile == null || !uomFile.getOriginalFilename().contains(".xlsx")){
			map.addAttribute("message", "Invalid File...");
		}
		else {
			List<Uom> uomList = util.processMultipart(uomFile);			
			//Do Validations
			if(uomList.isEmpty()){
				map.addAttribute("message", "No Records Found Plz Check Sheet Name...");
			}
			else {
				//Do Validation
				Map<String,List<String>> errorMap= validator.validateListUoms(uomList);
				
				if(errorMap.isEmpty()){
					service.saveFile(uomList);
					map.addAttribute("message", "Successfully Saved...");
				}
				else {
					map.addAttribute("errorMap", errorMap);
				}
			}
		}
		return "UomMultipart";
	}
	
	//Download DB Data in Excel Sheet
	@RequestMapping("/download")
	public ModelAndView getAllUom(){
		//Get List of Data From DB
		List<Uom> uomList= service.getAllUom();
		
		return new ModelAndView(new UomXlsxView(),"uomList",uomList);
	}
}
