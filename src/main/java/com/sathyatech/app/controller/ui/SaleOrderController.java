package com.sathyatech.app.controller.ui;

import java.util.Iterator;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sathyatech.app.config.EmailConfig;
import com.sathyatech.app.model.SaleOrder;
import com.sathyatech.app.model.SaleOrderDetails;
import com.sathyatech.app.service.ISaleOrderService;
import com.sathyatech.app.spec.SaleOrderSpecification;
import com.sathyatech.app.util.SaleOrderUtil;
import com.sathyatech.app.validator.SaleOrderDetailValidator;
import com.sathyatech.app.validator.SaleOrderValidator;
import com.sathyatech.app.view.CustomerInvoicePdfView;
import com.sathyatech.app.view.SaleOrderXlsxView;

/**
 * @author Pratik
 *
 */
@Controller
@RequestMapping("/sale")
public class SaleOrderController {

	@Autowired
	private ISaleOrderService saleService;

	@Autowired
	private SaleOrderUtil saleUtil;

	@Autowired
	private SaleOrderValidator validator;

	@Autowired
	private SaleOrderDetailValidator dtlValidator;

	@Autowired
	private EmailConfig emailConfig;

	@GetMapping("/register")
	public String showRegister(ModelMap map){
		map.addAttribute("saleOrder", new SaleOrder());
		saleUtil.addUi(map);
		return "SaleOrderRegister";
	}

	@PostMapping("/register")
	public String save(@ModelAttribute("saleOrder")SaleOrder saleOrder,Errors errors,ModelMap map){
		//Do Validations
		validator.validate(saleOrder, errors);
		if(!errors.hasErrors()){
			Long saleId = saleService.saveSale(saleOrder);
			map.addAttribute("saleOrder", new SaleOrder());
			map.addAttribute("message", "Sale Order Created with Id:"+saleId);
		}
		saleUtil.addUi(map);
		return "SaleOrderRegister";
	}

	@GetMapping("/viewAll")
	public String viewAll(@PageableDefault(size=3,sort="saleId",direction=Direction.ASC)Pageable pageable,@ModelAttribute("saleOrder")SaleOrder saleOrder,ModelMap map){
		SaleOrderSpecification spec = new SaleOrderSpecification(saleOrder);
		Page<SaleOrder> page= saleService.findAll(spec,pageable);
		map.addAttribute("stockSources", SaleOrderUtil.getStockSource());
		map.addAttribute("page", page);
		return "SaleOrderData";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long saleId){
		String page = "";
		if(saleService.isExist(saleId)){
			saleService.delete(saleId);
			page = "redirect:viewAll";
		}
		return page;
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("saleId")Long saleId,ModelMap map){
		SaleOrder saleOrder= saleService.findById(saleId);
		saleUtil.addUi(map);
		map.addAttribute("saleOrder", saleOrder);
		return "SaleOrderDataEdit";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("saleOrder")SaleOrder saleOrder,Errors errors,ModelMap map){
		validator.validate(saleOrder, errors);
		if(!errors.hasErrors()){
			saleService.update(saleOrder);
			return "redirect:viewAll";
		}
		else{
			saleUtil.addUi(map);
			return "SaleOrderDataEdit";
		}

	}

	//ShowSaleOrderItem
	@GetMapping("/addSoItems")
	public String showSaleOrderItem(@RequestParam Long saleId,ModelMap map){
		SaleOrder saleOrder = saleService.findById(saleId);

		SaleOrderDetails sod = new SaleOrderDetails();
		sod.setSaleOrdHdrId(saleOrder.getSaleId());
		//set serial Number
		sod.setSlno(saleOrder.getSaleDetails().size()+1);
		map.addAttribute("saleOrderDetails", sod);
		map.addAttribute("saleOrder", saleOrder);
		map.addAttribute("custItems", saleUtil.findItemByCustomer(saleOrder.getSaleCustomers().getWhUserId()));

		return "AddSaleOrderItems";
	}

	// Add item to SaleOrder
	@PostMapping("/addSoItems")
	public String addSaleOrderItem(@ModelAttribute("saleOrderDetails")SaleOrderDetails saleOrderDetails,@RequestParam("itemOperation")String itemOperation,Errors errors,ModelMap map){
		SaleOrder saleOrder = saleService.findById(saleOrderDetails.getSaleOrdHdrId());

		String page = "";
		//If We Click On Add Item then...
		if("Add Item".equals(itemOperation)){
			//Do Validation
			dtlValidator.validate(saleOrderDetails, errors);
			if(errors.hasErrors()){

				page = "addSoItems";
			}
			else{
				saleOrder.getSaleDetails().add(saleOrderDetails);
				saleOrder.setStatus("SALE-READY");//Can't Perform Any Changes in Customer
				saleService.saveSale(saleOrder);
				saleOrderDetails = new SaleOrderDetails();
				saleOrderDetails.setSaleOrdHdrId(saleOrder.getSaleId());
				saleOrderDetails.setSlno(saleOrder.getSaleDetails().size()+1);
				map.addAttribute("saleOrderDetails", saleOrderDetails);
			}
			map.addAttribute("custItems", saleUtil.findItemByCustomer(saleOrder.getSaleCustomers().getWhUserId()));
			map.addAttribute("saleOrder", saleOrder);
			page = "AddSaleOrderItems";
		}
		else if("Save and Continue".equals(itemOperation)){
			if(!saleOrder.getSaleDetails().isEmpty()){
				saleOrder.setStatus("SALE-CONFIRM");
				saleService.update(saleOrder);
				page = "redirect:viewAll";
			}
		}
		return page;
	}

	//Remove Item
	@GetMapping("/removeItem")
	public String remove(@RequestParam("slno")int slno,@RequestParam("saleOrdHdrId")Long saleOrdHdrId,ModelMap map){
		SaleOrder saleOrder= saleService.findById(saleOrdHdrId);
		List<SaleOrderDetails> saleDetails= saleOrder.getSaleDetails();
		if(saleDetails != null){
			Iterator<SaleOrderDetails> itr= saleDetails.iterator();
			while(itr.hasNext()){
				SaleOrderDetails soDetail= itr.next();
				if(soDetail.getSlno()==slno){ //list--> slno matched with slno remove
					itr.remove();
				}
			}
		}
		//Set Serial Numbers
		if(saleDetails != null && saleDetails.size() > 0){
			int slNo = 1;
			Iterator<SaleOrderDetails> itr= saleDetails.iterator();
			while(itr.hasNext()){
				SaleOrderDetails soDetail= itr.next();
				soDetail.setSlno(slNo++);
			}

		}

		if(saleDetails.size()==0)
			saleOrder.setStatus("SALE-OPEN");//We can change Customer

		saleService.update(saleOrder);

		SaleOrderDetails saleOrderDetail = new SaleOrderDetails();
		saleOrderDetail.setSaleOrdHdrId(saleOrder.getSaleId());
		saleOrderDetail.setSlno(saleOrder.getSaleDetails().size()+1);

		map.addAttribute("custItems", saleUtil.findItemByCustomer(saleOrder.getSaleCustomers().getWhUserId()));
		map.addAttribute("saleOrderDetails", saleOrderDetail);
		map.addAttribute("saleOrder", saleOrder);
		return "AddSaleOrderItems";
	}
	/*
	 * Cancel Sale Order
	 * Change Status & update 
	 */
	@GetMapping("/cancelSaleOrder")
	public String cancelSaleOrder(@RequestParam Long saleId){
		SaleOrder saleOrder = saleService.findById(saleId);
		saleOrder.setStatus("SALE-CANCELLED");
		saleService.update(saleOrder);
		return "redirect:viewAll";
	}

	/*
	 * Conform saleOrder..Change Status to SALE-INVOICED
	 */
	@GetMapping("/saleConform")
	public String conformSaleOrder(@RequestParam Long saleId){
		SaleOrder saleOrder= saleService.findById(saleId);
		saleOrder.setStatus("SALE-INVOICED");
		saleService.update(saleOrder);
		return "redirect:viewAll";
	}

	@GetMapping("/saleInvoceGen")
	public ModelAndView generateInvoice(@RequestParam Long saleId,RedirectAttributes r){
		SaleOrder saleOrder = saleService.findById(saleId);

		ModelAndView mav = null;
//saleOrder=null;
		if(saleOrder != null){

			if("SALE-INVOICED".equals(saleOrder.getStatus().toUpperCase())){

				//emailConfig.sendEmail(saleOrder.getSaleCustomers().getWhUserEmail(), "Invoice", "Download Below Attachment", null);

				mav = new ModelAndView(new CustomerInvoicePdfView(),"saleOrder",saleOrder);

			}
			else{
				mav=new ModelAndView(new SaleOrderXlsxView(),"saleOrderList",saleService.findAll());
			}
		
		}
		return mav;
		/*else{
			map.addAttribute("saleOrder", new SaleOrder());
		
			return new ModelAndView("redirect:viewAll");
		}*/
	}
}