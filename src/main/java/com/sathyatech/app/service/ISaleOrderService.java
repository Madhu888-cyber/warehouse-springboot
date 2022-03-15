package com.sathyatech.app.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.sathyatech.app.model.SaleOrder;

public interface ISaleOrderService {
	
	public Long saveSale(SaleOrder saleOrder);
	
	public Page<SaleOrder> findAll(Pageable pageable);
	
	public Page<SaleOrder> findAll(Specification<SaleOrder> spec,Pageable pageable);
	
	public SaleOrder findById(Long saleId);
	
	public void delete(Long saleId);
	
	public boolean isExist(Long saleId);
	
	public void update(SaleOrder saleOrder);
	
	public void saveFile(List<SaleOrder> saleOrders);
	
	public List<SaleOrder> findAll();
}
