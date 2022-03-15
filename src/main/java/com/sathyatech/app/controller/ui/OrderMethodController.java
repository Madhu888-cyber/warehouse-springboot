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
import org.springframework.web.servlet.ModelAndView;

import com.sathyatech.app.model.OrderMethod;
import com.sathyatech.app.service.IOrderMethodService;
import com.sathyatech.app.spec.OrderSpecification;
import com.sathyatech.app.util.OrderMethodUtils;
import com.sathyatech.app.validator.OrderValidator;
import com.sathyatech.app.view.OrderXlsxView;

@Controller
@RequestMapping("/order")
public class OrderMethodController {

	@Autowired
	private IOrderMethodService service;

	@Autowired
	private OrderValidator validator;

	//Show Reg
	@GetMapping("/register")
	public String showReg(ModelMap map){
		map.addAttribute("orderMethod", new OrderMethod());
		map.addAttribute("orderModes", OrderMethodUtils.getOrderModeList());
		map.addAttribute("orderMethds", OrderMethodUtils.getOrderMethdsList());
		map.addAttribute("orderAccepts", OrderMethodUtils.getOrderAcceptList());
		return "OrderMethodRegister";
	}

	//Save OrderMethod
	@PostMapping("/register")
	public String save(@ModelAttribute OrderMethod orderMethod, Errors errors, ModelMap map){
		//Do Validations
		validator.setEditMode(false);
		validator.validate(orderMethod, errors);
		if(!errors.hasErrors()){
			Long orderId = service.saveOrderMethod(orderMethod);
			map.addAttribute("orderMethod", new OrderMethod());
			map.addAttribute("message", "successfully Saved With Id="+orderId);
		}
		map.addAttribute("orderModes", OrderMethodUtils.getOrderModeList());
		map.addAttribute("orderMethds", OrderMethodUtils.getOrderMethdsList());
		map.addAttribute("orderAccepts", OrderMethodUtils.getOrderAcceptList());
		return "OrderMethodRegister";
	}

	/*//Get List Of Order
	@GetMapping("/allOrder")
	public String getAll(ModelMap map){
		List<Order> list= service.getAllOrderMethod();
		map.addAttribute("list", list);
		return "OrderMtData";
	}*/

	//Delete One Order
	@GetMapping("/delete")
	public String deleteOrder(@RequestParam Long orderId){
		service.deleteOrderMethod(orderId);
		return "redirect:viewAll";
	}

	//Get One Order Method
	@GetMapping("/update")
	public String updateOrder(@RequestParam Long orderId,ModelMap map){
		OrderMethod orderMethod= service.getOneOrderMethod(orderId);
		map.addAttribute("orderModes", OrderMethodUtils.getOrderModeList());
		map.addAttribute("orderMethds", OrderMethodUtils.getOrderMethdsList());
		map.addAttribute("orderAccepts", OrderMethodUtils.getOrderAcceptList());
		map.addAttribute("orderMethod", orderMethod);
		return "OrderMethodUpdate";
	}

	//Edit
	@PostMapping("/edit")
	public String edit(@ModelAttribute OrderMethod orderMethod,Errors errors,ModelMap map){
		//Do Validations Before Save
		validator.setEditMode(true);
		validator.validate(orderMethod, errors);
		String page = "";
		if(errors.hasErrors()){
			map.addAttribute("orderModes", OrderMethodUtils.getOrderModeList());
			map.addAttribute("orderMethds", OrderMethodUtils.getOrderMethdsList());
			map.addAttribute("orderAccepts", OrderMethodUtils.getOrderAcceptList());
			page = "OrderMethodUpdate";
		}
		else{
			service.updateOrderMethod(orderMethod);
			page = "redirect:viewAll";
		}
		return page;
	}

	/*Get All Data In Page using Pagination*/
	/*@GetMapping("/viewAll")
	public String findAll(@PageableDefault(size=3,sort="orderId",direction=Direction.ASC)Pageable pageable,ModelMap map){
		Page<Order> page = service.findAll(pageable);
		map.addAttribute("page", page);
		return "OrderMtData";
	}*/

	@GetMapping("/viewAll")
	public String findAll(@PageableDefault(size=3,sort="orderId",direction=Direction.ASC)Pageable pageable,
			@ModelAttribute OrderMethod orderMethod,ModelMap map){
		Specification<OrderMethod> spec = new OrderSpecification(orderMethod);
		Page<OrderMethod> page = service.findAll(spec,pageable);
		map.addAttribute("page", page);
		map.addAttribute("orderModes", OrderMethodUtils.getOrderModeList());
		map.addAttribute("orderMethds", OrderMethodUtils.getOrderMethdsList());
		map.addAttribute("orderAccepts", OrderMethodUtils.getOrderAcceptList());
		return "OrderMethodData";
	}

	//Download Excel Sheet 
	@RequestMapping("/download")
	public ModelAndView getAll(@PageableDefault(size=3,sort="orderId",direction=Direction.ASC) Pageable pageable)
	{
		Page<OrderMethod> page= service.findAll(pageable);
		//System.out.println(page.getNumber());
		return new ModelAndView(new OrderXlsxView(),"orderMethodList",page.getContent());
	}
}