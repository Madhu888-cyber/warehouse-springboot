package com.sathyatech.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.sathyatech.app.model.OrderMethod;



public interface IOrderMethodService {
	
	public Long saveOrderMethod(OrderMethod orderMethod);
	
	public List<OrderMethod> getAllOrderMethod();
	
	public void deleteOrderMethod(Long orderId);
	
	public OrderMethod getOneOrderMethod(Long orderId);
	
	public Boolean isExist(Long orderId);
	
	public void updateOrderMethod(OrderMethod orderMethod);
	
	public Page<OrderMethod> findAll(Pageable pageable);
	
	public void saveFile(List<OrderMethod> orderList);
	
	//Search
	public Page<OrderMethod> findAll(Specification<OrderMethod> spec,Pageable pageable);
	
	public boolean isOrderCodeAndMethodExist(String orderCode,String orderMethd);
	
	//Find By OrderMode
	public List<OrderMethod> findByOrderMode(String orderMode);
	
	//Find By OrderCode
	public OrderMethod findByOrderCode(String orderCode);
	
	
}
