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

import com.sathyatech.app.model.Item;
import com.sathyatech.app.service.IItemService;
import com.sathyatech.app.util.ItemUtil;
import com.sathyatech.app.validator.ItemMultipartValidator;
import com.sathyatech.app.view.ItemXlsxView;

@Controller
@RequestMapping("/multipart/items")
public class ItemMultipartController {

	@Autowired
	private ItemUtil itemUtil;

	@Autowired
	private IItemService itemService;

	@Autowired
	private ItemMultipartValidator validator;

	@GetMapping("/save")
	public String show(){
		return "ItemMultipart";
	}

	//Save File
	@PostMapping("/save")
	public String saveFile(@RequestParam("itemFile") MultipartFile itemFile,ModelMap map){

		if(itemFile == null || !itemFile.getOriginalFilename().contains(".xlsx")){
			map.addAttribute("message", "Invalid File");
		}
		else{
			List<Item> items = itemUtil.processMultipart(itemFile);

			if(items==null || items.isEmpty()){
				map.addAttribute("message", "No Records Found");
			}
			else{
				//Do Validation
				Map<String,List<String>> errorMap= validator.processValidation(items);

				if(errorMap.isEmpty()){
					itemService.saveFile(items);
					map.addAttribute("message", "Excel File Saved Successfully");
				}
				else{
					map.addAttribute("errorMap", errorMap);
				}
			}
		}
		return "ItemMultipart";
	}

	//Download
	@GetMapping("/download")
	public ModelAndView downloadFile(){
		List<Item> items= itemService.findAll();
		return new ModelAndView(new ItemXlsxView(),"items",items);
	}
}
