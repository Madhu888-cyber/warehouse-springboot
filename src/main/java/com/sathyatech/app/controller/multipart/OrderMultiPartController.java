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
import com.sathyatech.app.model.OrderMethod;
import com.sathyatech.app.service.IOrderMethodService;
import com.sathyatech.app.util.OrderMethodUtils;
import com.sathyatech.app.validator.OrderMultipartValidator;
import com.sathyatech.app.view.OrderXlsxView;

@Controller
@RequestMapping("/multipart/orders")
public class OrderMultiPartController {

	@Autowired
	private IOrderMethodService service;
	
	@Autowired
	private OrderMethodUtils u;

	@Autowired
	private OrderMultipartValidator validator;

	@GetMapping("/show")
	public String show(){
		return "OrderMethodMultipartData";
	}

	//Save
	@PostMapping("/save")
	public String saveFile(@RequestParam("orderFile") MultipartFile orderFile,ModelMap map){
		//Check File Is Valid Or Not
		if(orderFile == null || !orderFile.getOriginalFilename().contains(".xlsx")){
			map.addAttribute("message", "Invalid File...");
		}
		else{
			//Convert File To List
			List<OrderMethod> orderList= u.convertMultipartToList(orderFile);

			if(orderList.isEmpty()){
				map.addAttribute("message", "No Records Found Plz Check Sheet Name...");
			}
			else{
				//Do Validation
				Map<String,List<String>> errorMap= validator.doValidation(orderList);

				if(errorMap.isEmpty()){
					service.saveFile(orderList);
					map.addAttribute("message", "Record Saved Successfully");
				}
				else{
					map.addAttribute("errorMap", errorMap);
				}
			}
		}
		return "OrderMethodMultipartData";
	}
	
	//Download in Excel
	@RequestMapping("/download")
	public ModelAndView getAll(){
		List<OrderMethod> orderMethodList = service.getAllOrderMethod();		
		return new ModelAndView(new OrderXlsxView(), "orderMethodList", orderMethodList);
	}
	
}
