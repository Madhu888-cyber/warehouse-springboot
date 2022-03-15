package com.sathyatech.app.controller.multipart;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.sathyatech.app.model.WHUserType;
import com.sathyatech.app.service.IWHUserTypeService;
import com.sathyatech.app.util.WHUserTypeUtil;
import com.sathyatech.app.validator.WHUserTypeMultipartValidator;
import com.sathyatech.app.view.WHUserTypeXlsxView;

@Controller
@RequestMapping("/multipart/users")
public class WHUserTypeMultipartController {
	
	@Autowired
	private IWHUserTypeService service;
	
	@Autowired
	private WHUserTypeUtil util;
	
	@Autowired
	private WHUserTypeMultipartValidator validator;
	
	//SHow Upload Page	
	@GetMapping("/show")
	public String show()
	{
		return "WHUserTypeMultipart";
	}
	
	//Save File
	@PostMapping("/upload")
	public String saveFile(@RequestParam MultipartFile userFile,ModelMap map){
		
		if(userFile == null || !userFile.getOriginalFilename().contains(".xlsx")){
			map.addAttribute("message", "Invalid File");
		}
		else{
			//Convert MultipartFile To List
			List<WHUserType> whUserTypes = util.convertMultipartToList(userFile);
			
			if(whUserTypes.isEmpty()){
				map.addAttribute("message", "No Records Found Plz Check Sheet Name...");
			}
			else{
				//Do Validation
				Map<String,List<String>> errorMap= validator.doValidation(whUserTypes);
				
				if(errorMap.isEmpty()){
					service.saveFile(whUserTypes);
					map.addAttribute("message", "SuccessFully Saved");
				}
				else{
					map.addAttribute("errorMap", errorMap);
				}
			}
		}
		return "WHUserTypeMultipart";
	}
	
	//Download
	@GetMapping("/download")
	public ModelAndView exportFile(){
		List<WHUserType> whUserTypes = service.findAll();
		return new ModelAndView(new WHUserTypeXlsxView(), "whUserTypes", whUserTypes);
	}
}