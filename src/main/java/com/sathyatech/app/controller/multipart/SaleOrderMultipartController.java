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

import com.sathyatech.app.model.SaleOrder;
import com.sathyatech.app.service.ISaleOrderService;
import com.sathyatech.app.util.SaleOrderUtil;
import com.sathyatech.app.validator.SaleOrderMultipartValidator;
import com.sathyatech.app.view.SaleOrderXlsxView;

@Controller
@RequestMapping("/sale/multipart")
public class SaleOrderMultipartController {
	
	@Autowired
	private ISaleOrderService service;
	
	@Autowired
	private SaleOrderUtil saleUtil;
	
	@Autowired
	private SaleOrderMultipartValidator validator;
	
	@GetMapping("/save")
	public String showMultipart(){
		return "SaleOrderMultipart";
	}
	
	@PostMapping("/save")
	public String saveFile(@RequestParam MultipartFile saleFile,ModelMap map){
		
		if(saleFile == null || !saleFile.getOriginalFilename().contains(".xlsx")){
			map.addAttribute("message", "Invalid File");
		}
		else{
			List<SaleOrder> saleOrders= saleUtil.convertMultipartToList(saleFile);
			if(saleOrders.isEmpty()){
				map.addAttribute("message", "No records Found Plz Check Sheet Name");
			}
			else{
				//Do Validation
				Map<String,List<String>> errorMap= validator.multipartValidator(saleOrders);
				
				if(errorMap.isEmpty()){
					service.saveFile(saleOrders);
					map.addAttribute("message", "File Saved Successfully");
				}
				else{
					map.addAttribute("errorMap", errorMap);
				}
			}
		}
		return "SaleOrderMultipart";
	}
	
	//Export
	@GetMapping("/download")
	public ModelAndView export(){
		List<SaleOrder> saleOrderList= service.findAll();
		return new ModelAndView(new SaleOrderXlsxView(),"saleOrderList",saleOrderList);
	}
}