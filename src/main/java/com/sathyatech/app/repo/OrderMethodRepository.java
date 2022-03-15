package com.sathyatech.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sathyatech.app.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Long>,JpaSpecificationExecutor<OrderMethod> {
	
	@Query("select count(orderId) from com.sathyatech.app.model.OrderMethod where orderCode=?1 and orderMethd=?2")
	public long countOrderCodeAndMethod(String orderCode,String orderMethd);
	
	public List<OrderMethod> findByOrderMode(String orderMode);
	
	//Fetch OrderMethod Based On Order Code
	public OrderMethod findByOrderCode(String orderCode);
}
