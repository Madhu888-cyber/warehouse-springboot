package com.sathyatech.app.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sathyatech.app.model.OrderMethod;
import com.sathyatech.app.repo.OrderMethodRepository;
import com.sathyatech.app.service.IOrderMethodService;


@Service
public class OrderMethodServiceImpl implements IOrderMethodService {
	
	@Autowired
	private OrderMethodRepository repo;
	//Save
	public Long saveOrderMethod(OrderMethod orderMethod) {
		orderMethod = repo.save(orderMethod);
		return orderMethod.getOrderId();
	}

	//Get All
	public List<OrderMethod> getAllOrderMethod() {
		List<OrderMethod> list= repo.findAll();
		Collections.sort(list, new Comparator<OrderMethod>() {
			
			public int compare(OrderMethod o1, OrderMethod o2) {	
				return o1.getOrderId().compareTo(o2.getOrderId());
			}
			
		});
		return list;
	}

	//Delete
	public void deleteOrderMethod(Long orderId) {
		repo.delete(orderId);
	}

	//Get One OrderMethod
	public OrderMethod getOneOrderMethod(Long orderId) {
		OrderMethod orderMethod = repo.getOne(orderId);
		return orderMethod;
	}
	
	//Id Exist or Not
	public Boolean isExist(Long orderId) {
		return repo.exists(orderId);
	}

	//Update Order Method
	public void updateOrderMethod(OrderMethod orderMethod) {
		orderMethod.setCreatedOn(repo.getOne(orderMethod.getOrderId()).getCreatedOn());
		repo.save(orderMethod);
	}
	
	/*FindALL Using Pagination*/
	public Page<OrderMethod> findAll(Pageable pageable) {
		Page<OrderMethod> page = repo.findAll(pageable);
		return page;
	}
	
	//Save File
	@Override
	public void saveFile(List<OrderMethod> orderList) {
		repo.save(orderList);
	}
	
	//Search
	@Override
	public Page<OrderMethod> findAll(Specification<OrderMethod> spec, Pageable pageable) {
		return repo.findAll(spec, pageable);
	}
	
	public boolean isOrderCodeAndMethodExist(String orderCode, String orderMethd) {
		long count = repo.countOrderCodeAndMethod(orderCode, orderMethd);
		if (count==0)
			return false;
		else 
			return true;
	}
	
	
	//By OrderMode
	public List<OrderMethod> findByOrderMode(String orderMode) {
		return repo.findByOrderMode(orderMode);
	}
	
	//By OrderCode ret OrderMethod
	public OrderMethod findByOrderCode(String orderCode) {
		return repo.findByOrderCode(orderCode);
	}
}
